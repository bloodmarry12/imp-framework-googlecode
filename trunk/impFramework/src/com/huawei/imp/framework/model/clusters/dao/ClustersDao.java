/**
 * 
 */
package com.huawei.imp.framework.model.clusters.dao;

import java.util.List;

import com.huawei.imp.framework.common.dao.IbatisSupportDAO;
import com.huawei.imp.framework.model.clusters.domain.SlaveDevice;

/**
 * @author ahli
 *
 */
public interface ClustersDao extends IbatisSupportDAO {

	List<SlaveDevice> queryForList(String localhost);
}
