package com.gfs.operatecenter.service;

import com.gfs.operatecenter.entity.vo.qiniu.QiniuVoRequest;
import com.gfs.operatecenter.entity.vo.qiniu.QiniuVoResponse;

import java.io.IOException;
import java.util.Set;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.service.impl
 * @ClassName QiniuService
 * @description
 * @date created in 2020-06-05 14:01
 * @modified by
 */
public interface QiniuService {
    /**
     * 客户端获取上传所需token
     *
     * @return
     */
    QiniuVoResponse clientUploadToken();

//	/**
//	 * 七牛云直接保存链接地址的内容到云空间，不用先下载到本地再上传 用于保存驰声录音到七牛云
//	 *
//	 * @param remoteUrl
//	 * @return
//	 */
//	String fetchRemoteUrl(String remoteUrl);

    /**
     * 七牛云删除云空间上传的文件
     *
     * @param fileName
     * @return
     */
    String deleteFileByName(String fileName);


    /**
     * 删除七牛云未使用的文件
     * @param qiniu
     * @return 删除的文件名
     */
    String deleteUnUseFiles(QiniuVoRequest qiniu);

    /**
     * 保存七牛临时上传文件名
     * @param qiniu
     * @return
     */
    Set<String> saveTempFile(QiniuVoRequest qiniu);

    /**
     * 上传文件到七牛
     *
     * @param localFilePath 要上传的文件全路径
     * @param key           上传到七牛后的文件名称
     */
    String uploadFile(String localFilePath, String key);


    /**
     * 从七牛下载文件
     *
     * @param urlStr   下载文件全路径
     * @param fileName 保存名称
     * @param savePath 保存路径
     */
    String downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException;


    /**
     * 获取七牛空间上的文件的大小
     *
     * @param url 七牛上边的文件全路径
     * @return
     */
    Long getFileSize(String url);
}
