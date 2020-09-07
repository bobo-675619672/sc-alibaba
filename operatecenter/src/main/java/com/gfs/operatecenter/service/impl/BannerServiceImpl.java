package com.gfs.operatecenter.service.impl;

import com.gfs.common.util.DateUtil;
import com.gfs.operatecenter.dao.BannerMapper;
import com.gfs.operatecenter.entity.table.Banner;
import com.gfs.operatecenter.entity.vo.banner.BannerMainVo;
import com.gfs.operatecenter.entity.vo.banner.BannerVo;
import com.gfs.operatecenter.entity.vo.feign.StudentInfoResponseVo;
import com.gfs.operatecenter.exception.GfsRuntimeException;
import com.gfs.operatecenter.feign.client.UserFeignClient;
import com.gfs.operatecenter.service.BannerService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.service.impl
 * @ClassName BannerServiceImpl
 * @description
 * @date created in 2020-05-18 10:41
 * @modified by
 */
@Slf4j
@Service
@Transactional
public class BannerServiceImpl implements BannerService{
    @Autowired
    UserFeignClient userFeignClient;
    @Autowired
    BannerMapper bannerMapper;


    /**
     *获取pc端首页轮播图
     * @param channelType
     * @return
     */
    @Override
    public BannerMainVo queryBannerMainForPc(String channelType) {

        List<Banner> banners = null;
        BannerMainVo bannerMainVo = new BannerMainVo();
        List<BannerVo> bannerVoList = Lists.newArrayList();
        List<BannerVo> bannerMallList = Lists.newArrayList();
        HashMap<String, Object> params = Maps.newHashMap();

        // 社区跳过白名单校验，因为获取不了token
//        if (BLOG_TYPE.equals(channelType)) {
//            params.put("channelType", channelType);
//            banners = bannerMapper.queryBannerForApp(params);
//            banners.forEach(getBannerConsumer(bannerVoList));
//            bannerMainVo.setBanners(bannerVoList);
//            // 轮播集合
//            return bannerMainVo;
//        }

        try {
            // 学生信息
            StudentInfoResponseVo studentInfo = userFeignClient.info().getData();
            if (!StringUtils.isEmpty(studentInfo)) {
                log.info("【app轮播: 账户={},地区={},学校ID={}】", studentInfo.getPhone(), studentInfo.getRegion(), studentInfo.getSchoolId());
                // 体验用户 （已登录，但只有号码信息，没有学校相关信息）
                if (studentInfo.getPhone() == null || studentInfo.getRegion() == null || studentInfo.getSchoolId() == null) {
                    log.info("体验用户");
                    params.put("channelType", channelType);
                    banners = bannerMapper.queryBannerForPc(params);
                    if (!CollectionUtils.isEmpty(banners)) {
                        banners.forEach(getBannerConsumer(bannerVoList));
                    }
                } else {
                    log.info("正常登录用户");
                    params.put("phone", studentInfo.getPhone());
                    params.put("region", studentInfo.getRegion());
                    params.put("bookId", studentInfo.getBookId());
                    params.put("classesId", studentInfo.getClassesId());
                    params.put("schoolId", studentInfo.getSchoolId().toString());
                    params.put("channelType", channelType);
                    banners = bannerMapper.queryBannerForPc(params);
                    if (!CollectionUtils.isEmpty(banners)) {
                        banners.forEach(getBannerConsumer(bannerVoList));
                    }
                }
            }
        } catch (GfsRuntimeException e) {
//            log.info("未登录用户");
//            params.put("channelType", channelType);
//            // 关联白名单关系
//            params.put("extend", "extend");
//            banners = bannerMapper.dynamicQuery(params);
//            if (!CollectionUtils.isEmpty(banners)) {
//                banners.forEach(getBannerConsumer(bannerVoList));
//            }
        }
        //设置商城bannerMallList
//        setPcPopout(bannerMainVo, bannerVoList, bannerMallList);
        bannerMainVo.setBanners(bannerVoList);
        return bannerMainVo;
    }

    // 轮播图有效时间范围，如果没有设置开始和结束时间，就直接返显
    private Consumer<Banner> getBannerConsumer(List<BannerVo> bannerVoList) {
        return e -> {
            BannerVo bannerVo = new BannerVo();
            BeanUtils.copyProperties(e, bannerVo);

            // 嵌套 广告跳转方式 -> APP内部 参数返回
//            BannerJump bannerJump = e.getBannerJump();
//            BannerJumpVo bannerJumpVo = new BannerJumpVo();
//            if (bannerJump != null) {
//                BeanUtils.copyProperties(bannerJump, bannerJumpVo);
//                // 构造数据
//                BuildVoResponse voBuildVoResponse = new BuildVoResponse();
//                BeanUtils.copyProperties(bannerJumpVo, voBuildVoResponse);
//                // 判断内容
//                if (bannerJumpVo.getMallContentType() != null) {
//                    voBuildVoResponse.setContentType(bannerJumpVo.getMallContentType());
//                }
//                contentsService.buildVo(voBuildVoResponse, false);
//                // 填充数据
//                bannerJumpVo.setUrl(voBuildVoResponse.getUrl());
//                bannerJumpVo.setRecordId(voBuildVoResponse.getRecordId());
//                bannerJumpVo.setExtendObj(voBuildVoResponse.getExtendObj());
//                bannerVo.setBannerJumpVo(bannerJumpVo);
//            }

            try {
                // 时间范围
                if (bannerVo.getStartTime() != null && bannerVo.getFinalTime() != null) {
                    String finalTime = DateUtil.getDateStr(bannerVo.getFinalTime(), "yyyy-MM-dd 23:59:59");
                    boolean timeInRange = DateUtil.isTimeInRange(bannerVo.getStartTime(), DateUtil.getDate(finalTime));
                    if (timeInRange) {
                        bannerVoList.add(bannerVo);
                    }
                } else {
                    bannerVoList.add(bannerVo);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
    }

    private void setPcPopout(BannerMainVo bannerMainVo, List<BannerVo> bannerVoList, List<BannerVo> bannerMallList) {
//        for (BannerVo bannerVo : bannerVoList) {
//            // pc弹框广告 = 8
//            if ("8".equals(bannerVo.getChannelType()) && bannerVo.getBannerJumpVo() != null) {
//                // 商品ID+定价ID 查询是否存在 逻辑是 如果系 >0 不显示,  =0 就显示轮播图
//                List<Map<String, String>> mapList = orderUserserviceService.isCanBuyActiveGoods(bannerVo.getBannerJumpVo().getPriceId());
//                if (null == mapList || mapList.size() == 0) {
//                    bannerMallList.add(bannerVo);
//                }
//            }
//        }
        if (!CollectionUtils.isEmpty(bannerMallList)) {
            // 只获取集合最后一条, 即最新一条 (排序按SQL：queryBannerForApp)
            BannerVo lastElement = getLastElement(bannerMallList);
            bannerMainVo.setBannerMalls(lastElement);
        }
    }

    /**
     * 获取list中存放的最后一个元素
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T getLastElement(List<T> list) {
        return list.get(list.size() - 1);
    }
}
