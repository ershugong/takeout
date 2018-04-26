package cn.web.takeout.dao;

import cn.web.takeout.vo.AcountVO;

import java.util.List;

public interface IAccountDao {
    /**
     * 获取菜单类型的销售统计
     * @param shopId
     * @return
     * @throws Exception
     */
    List<AcountVO> getMenuTypeAccount(String shopId) throws Exception;

    /**
     * 获取评价类型的统计
     * @param shopId
     * @return
     * @throws Exception
     */
    List<AcountVO> getCommentTypeAcount(String shopId) throws Exception;
}
