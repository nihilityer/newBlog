package com.nihilityer.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nihilityer.entity.WebInfo;
import com.nihilityer.mapper.WebInfoMapper;
import org.springframework.stereotype.Service;

/**
 * @PackageName com.nihilityer.service
 * @ClassName UpdateVisits
 * @Description 关于网站信息的数据库访问service
 * @Author nihilityer
 * @Date 2021/9/16 20:45
 */
@Service
public class UpdateWebInfoService {

    private final WebInfoMapper webInfoMapper;

    public UpdateWebInfoService(WebInfoMapper webInfoMapper) {
        this.webInfoMapper = webInfoMapper;
    }

    /**
     *
     * @return 返回WebInfo对象,set_name值为VisitsNumber
     */
    private WebInfo visitsInfo() {
        return webInfoMapper.selectOne(visitsWrapper());
    }

    /**
     *
     * @return 返回visitsNumber的wrapper对象
     */
    private QueryWrapper<WebInfo> visitsWrapper() {
        QueryWrapper<WebInfo> webInfoQueryWrapper = new QueryWrapper<>();
        return webInfoQueryWrapper.eq("set_name", "viewNumber");
    }

    /**
     *  添加一条访问记录
     * @return 返回是否正常更新，只有更新一条数据时才正常
     */
    public boolean addVisitsOne() {
        WebInfo webInfo = visitsInfo();
        webInfo.setSetNumber(getVisitsNumber() + 1);
        return webInfoMapper.update(webInfo, visitsWrapper()) == 1;
    }

    /**
     *
     * @return 返回网站被访问量
     */
    public int getVisitsNumber() {
        return visitsInfo().getSetNumber();
    }

    /**
     *
     * @return 返回WebInfo对象,set_name值为BlogNumber
     */
    private WebInfo blogNumberInfo() {
        return webInfoMapper.selectOne(blogNumberWrapper());
    }


    /**
     *
     * @return 返回blogNumber的wrapper对象
     */
    private QueryWrapper<WebInfo> blogNumberWrapper() {
        QueryWrapper<WebInfo> webInfoQueryWrapper = new QueryWrapper<>();
        return webInfoQueryWrapper.eq("set_name", "blogNumber");
    }

    /**
     *
     * @return 返回是否添加博客数量是否成功
     */
    public boolean addBlogNumberOne() {
        WebInfo webInfo = blogNumberInfo();
        webInfo.setSetNumber(getBlogNumber() + 1);
        return webInfoMapper.update(webInfo, blogNumberWrapper()) == 1;
    }

    /**
     *
     * @return 返回博客的数量
     */
    public int getBlogNumber() {
        return blogNumberInfo().getSetNumber();
    }

    /**
     *
     * @return 返回减少一条博客记录是否成功
     */
    public boolean reduceBlogNumberOne() {
        WebInfo webInfo = blogNumberInfo();
        webInfo.setSetNumber(getBlogNumber() - 1);
        return webInfoMapper.update(webInfo, blogNumberWrapper()) == 1;
    }

}
