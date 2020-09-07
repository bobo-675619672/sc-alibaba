package com.gfs.operatecenter.service.impl;

import com.gfs.common.constant.RedisConstant;
import com.gfs.common.enums.ResultEnum;
import com.gfs.operatecenter.constant.QiniuConstant;
import com.gfs.operatecenter.entity.vo.qiniu.QiniuVoRequest;
import com.gfs.operatecenter.entity.vo.qiniu.QiniuVoResponse;
import com.gfs.operatecenter.exception.GfsRuntimeException;
import com.gfs.operatecenter.service.QiniuService;
import com.gfs.operatecenter.util.JsonUtils;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FetchRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.service.impl
 * @ClassName QiniuServiceImpl
 * @description
 * @date created in 2020-06-05 14:01
 * @modified by
 */
@Slf4j
@Service
@Transactional
public class QiniuServiceImpl implements QiniuService {


    private final String QINIU_PRE = "QINIU_";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 客户端获取上传所需token
     *
     * @return
     */
    @Override
    public QiniuVoResponse clientUploadToken() {
        String token = Auth.create(QiniuConstant.ACCESSKEY, QiniuConstant.SECRETKEY).uploadToken(QiniuConstant.BUCKET);
        QiniuVoResponse qiniuVoResponse = new QiniuVoResponse();
        qiniuVoResponse.setToken(token);
        qiniuVoResponse.setDomain(QiniuConstant.DOMAIN);
        qiniuVoResponse.setUploadHttp(QiniuConstant.CLIENTUPLOADHTTP);
        qiniuVoResponse.setUploadHttps(QiniuConstant.CLIENTUPLOADHTTPS);
        return qiniuVoResponse;
    }

    /**
     * 删除七牛云未使用的文件
     *
     * @param qiniu
     * @return
     */
    @Override
    public String deleteUnUseFiles(QiniuVoRequest qiniu) {
        String key = QINIU_PRE + qiniu.getKey();
        List<String> useFiles = qiniu.getFileNames();
        StringBuffer stringBuffer = new StringBuffer("删除文件:");
        //临时保存的文件
        try {
            Set<String> tempFiles = JsonUtils.jsonToSet(stringRedisTemplate.opsForValue().get(key), String.class);
            //如果没有文件，不作处理
            if (tempFiles.size() <= 0) {
                return "没有可删除的文件";
            }

            //如果都未使用,则删除临时文件 所有的文件
            if (useFiles == null || useFiles.size() <= 0) {
                for (String fileName : tempFiles) {
                    stringBuffer.append(fileName).append(";");
                    //删除七牛云上的文件
                    deleteFileByName(fileName);
                }
                return stringBuffer.toString();
            }


            for (String fileName : tempFiles) {
                if (!useFiles.contains(fileName)) {
                    //删除七牛云上的文件
                    stringBuffer.append(fileName).append(";");
                    deleteFileByName(fileName);
                }
            }
        } catch (Exception e) {
            log.error("删除七牛云未使用的文件错误：", e);
        } finally {
            //删除缓存
            stringRedisTemplate.delete(key);
        }
        //返回删除的文件名
        return stringBuffer.toString();
    }

    /**
     * 保存七牛临时上传文件名
     *
     * @param qiniu
     * @return
     */
    @Override
    public Set<String> saveTempFile(QiniuVoRequest qiniu) {
        String key = QINIU_PRE + qiniu.getKey();
        Set<String> fileNames;
        try {
            fileNames = JsonUtils.jsonToSet(stringRedisTemplate.opsForValue().get(key), String.class);
        } catch (Exception e) {
            fileNames = new HashSet<>();
        }
        fileNames.add(qiniu.getFileName());
        // 放入缓存，并设置缓存时间为1天
        stringRedisTemplate.opsForValue().set(key, JsonUtils.beanToJson(fileNames), RedisConstant.ONE_DAY, TimeUnit.SECONDS);

        return fileNames;
    }

//	/**
//	 * 七牛云直接保存链接地址的内容到云空间，不用先下载到本地再上传 用于保存驰声录音到七牛云
//	 *
//	 * @param remoteUrl
//	 * @return
//	 */
//	@Override
//	public String fetchRemoteUrl(String remoteUrl) {
//		Configuration cfg = new Configuration(Zone.zone2());
//		Auth auth = Auth.create(QiniuConstant.ACCESSKEY, QiniuConstant.SECRETKEY);
//		BucketManager bucketManager = new BucketManager(auth, cfg);
//		try {
//			FetchRet ret = bucketManager.fetch(remoteUrl, QiniuConstant.BUCKET);
//			log.info("Fetch {} to {}, {}, {}", remoteUrl, ret.key, ret.mimeType, ret.fsize);
//			return QiniuConstant.DOMAIN + ret.key;
//		} catch (QiniuException e) {
//			log.error("Fetch " + remoteUrl, e);
//		}
//		return null;
//	}

    /**
     * 七牛云删除云空间文件
     *
     * @param fileName
     * @return
     */
    @Override
    public String deleteFileByName(String fileName) {
        String msg = "OK";
        try {
            Auth auth = Auth.create(QiniuConstant.ACCESSKEY, QiniuConstant.SECRETKEY);
            Configuration config = new Configuration(Zone.autoZone());
            BucketManager bucketMgr = new BucketManager(auth, config);
            //指定需要删除的文件，和文件所在的存储空间
            bucketMgr.delete(QiniuConstant.BUCKET, handFileName(fileName));//当前为7.2.1；  7.2.2后才能传多个key ，即：第二个参数为数组 (String... deleteTargets)
        } catch (QiniuException e) {
            log.error("七牛云删除文件错误：", e);
            msg = "文件不存在或已删除";
        }
        return msg;
    }

    private String handFileName(String fileName) {
        //包含连接前缀  http://xx.gfs100.cn
        if (fileName.startsWith("http")) {
            return fileName.split("/")[1];
        }

        return fileName;
    }

    /**
     * 上传文件到七牛
     *
     * @param localFilePath 要上传的文件全路径
     * @param key           上传到七牛后的文件名称
     */
    @Override
    public String uploadFile(String localFilePath, String key) {
        String res = "";
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = QiniuConstant.ACCESSKEY;
        String secretKey = QiniuConstant.SECRETKEY;
        String bucket = QiniuConstant.BUCKET;
//如果是Windows情况下，格式是 D:\\qiniu\\test.png
        //String localFilePath = "/home/qiniu/test.png";
//默认不指定key的情况下，以文件内容的hash值作为文件名
        //String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            System.out.println(putRet.key);
//            System.out.println(putRet.hash);
            res = putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return res;
    }


    /**
     * 从七牛下载文件
     *
     * @param urlStr   下载文件全路径
     * @param fileName 保存名称
     * @param savePath 保存路径
     * @throws IOException
     */
    @Override
    public String downLoadFromUrl(String urlStr, String fileName, String savePath) {
        FileOutputStream fos = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(3 * 1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            //得到输入流
            inputStream = conn.getInputStream();
            //获取自己数组
            byte[] getData = readInputStream(inputStream);

            //文件保存位置
            File saveDir = new File(savePath);
            if (!saveDir.exists() && !saveDir.mkdir()) {
                log.error("创建文件失败");
            }
            File file = new File(saveDir + File.separator + fileName);
            fos = new FileOutputStream(file);
            fos.write(getData);
            return savePath;
        } catch (Exception e) {
            log.error("下载七牛文件错误：", e);
            throw new GfsRuntimeException(e, ResultEnum.DOWNLOAD_FILE_ERROR, "下载文件异常");
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                log.error("关闭流错误：", e);
                //throw new GaofenshuoRuntimeException(e, ResultEnum.DOWNLOAD_FILE_ERROR, "下载文件异常");
            }

        }

    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    /**
     * 获取七牛空间上的文件的大小
     *
     * @param url 七牛上边的文件全路径
     * @return
     */
    @Override
    public Long getFileSize(String url) {
        try {
            Auth auth = Auth.create(QiniuConstant.ACCESSKEY, QiniuConstant.SECRETKEY);
            Configuration config = new Configuration(Zone.autoZone());
            BucketManager bucketMgr = new BucketManager(auth, config);
            FetchRet putret = bucketMgr.fetch(url, QiniuConstant.BUCKET);
            return putret.fsize;
        } catch (QiniuException e) {
            return -1L;
        }
    }


// public static void main(String[] args) {
    // QiniuServiceImpl qiniuService = new QiniuServiceImpl();
    //测试下载文件
//        String saveUrl = "D:\\";
//        String name = "09f1c59c42234254ba2f53d88a6e367b";
//        String qiniuPath = QiniuConstant.DOMAIN + name;
//        try {
//            qiniuService.downLoadFromUrl(qiniuPath, name, saveUrl);
//        } catch (Exception e) {
//
//        }

    //测试上传文件
//        String localFilePath = "D:\\silentVideo.mp4";
//        String key = "silentVideo.mp4";
//        String s = qiniuService.uploadFile(localFilePath, key);


    //测试七牛获取文件大小
//        String name="111222.jpg";
//        Long l = qiniuService.getFileSize(QiniuConstant.DOMAIN + name);
//        System.out.println(l);
    //}
}
