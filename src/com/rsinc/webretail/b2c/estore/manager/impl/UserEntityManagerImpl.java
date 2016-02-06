/**
 * 
 */
package com.rsinc.webretail.b2c.estore.manager.impl;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.rsinc.webretail.b2c.estore.dao.BaseDao;
import com.rsinc.webretail.b2c.estore.dao.UserDao;
import com.rsinc.webretail.b2c.estore.domain.UserBean;
import com.rsinc.webretail.b2c.estore.domain.enums.UserStatus;
import com.rsinc.webretail.b2c.estore.exception.BeanValidationException;
import com.rsinc.webretail.b2c.estore.manager.UserEntityManager;
import com.rsinc.webretail.b2c.estore.util.Constants;

/**O
 * @author Roshan Titus 
 *
 */
@Component
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class UserEntityManagerImpl extends BaseEntityManagerImpl<UserBean> implements UserEntityManager {

	/**
	 * 
	 */
	@Inject
	private UserDao<UserBean> userDao;
	
	public UserEntityManagerImpl() {
	}
	
	@Override
	public void setDefaultValues(UserBean userBean) {
		if(null == userBean)
		{
			throw new IllegalArgumentException("BaseBean object is null"); 
		}			
		if(null == userBean.getRewardPoints())
		{
			userBean.setRewardPoints(Constants.ZERO);
		}
		if(null == userBean.getLocaleCode())
		{
			userBean.setLocaleCode(Constants.DEFAULT_LOCALE);
		}	
		if(null == userBean.getSubscribedForNewsLetterYN())
		{
			userBean.setSubscribedForNewsLetterYN(Constants.False);
		}		
		if(null == userBean.getStatus())
		{
			userBean.setStatus(UserStatus.NEW.toString());
		}		
		super.setDefaultValues(userBean);
	}

	@Override
	public void validateForCreate(UserBean userBean) throws BeanValidationException {
		if(null == userBean)
		{
			throw new IllegalArgumentException("UserBean object cannot be null"); 
		}	
//		if(null == userBean.getParty())
//		{
//			throw new IllegalArgumentException("Party object in UserBean cannot be null"); 
//		}
		super.validateForCreate(userBean);
	}

	@Override
	public BaseDao<UserBean> getDao() {
		return userDao;
	}

	public void setUserDao(UserDao<UserBean> userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UserBean loadById(Object id){

		return load(UserBean.class, id);
	}

	@Override
	public void deleteById(Object id){
		
		deleteById(UserBean.class, id);
	}
}
