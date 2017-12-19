package com.qwm.bos.dao.base.impl;

import com.qwm.bos.dao.base.IBaseDao;
import com.qwm.bos.utils.PageBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/11 下午9:53
 * @className: BaseDaoImpl
 * @description:
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

    //代表的是某个实体的类型
    private Class<T> entityClass;

    @Resource//根据类型注入spring工厂中的会话工厂对象sessionFactory
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    public BaseDaoImpl(){
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取父类上声明的泛型数组
        Type[] actualTypeArguments = superclass.getActualTypeArguments();
        entityClass = (Class<T>)actualTypeArguments[0];
    }

    @Override
    public void save(T entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public void delete(T entity) {
        getHibernateTemplate().delete(entity);
    }

    @Override
    public void update(T entity) {
        getHibernateTemplate().update(entity);
    }

    @Override
    public T findById(Serializable id) {
        return getHibernateTemplate().get(entityClass,id);
    }

    @Override
    public List<T> findAll() {
        String hql = "FROM "+entityClass.getSimpleName();
        return (List<T>)getHibernateTemplate().find(hql);
    }

    /**
     * 执行更新
     * @param queryName
     * @param objects
     */
    @Override
    public void executeUpdate(String queryName, Object... objects) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.getNamedQuery(queryName);
        for (int i = 0; i < objects.length; i++) {
            //为HQL语句中 ? 赋值
            query.setParameter(i,objects[i]);
        }
        //执行更新
        query.executeUpdate();
    }

    /**
     * 分页查询
     */
    @Override
    public void pageQuery(PageBean pageBean) {
        int currentPage = pageBean.getCurrentPage();
        int pageSize = pageBean.getPageSize();
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();

        //查询total---总数据量
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> countList = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        Long count = countList.get(0);
        pageBean.setTotal(count.intValue());

        //查询rows---当前页需要展示的数据集合
        detachedCriteria.setProjection(null);//指定hibernate框架发出sql的形式----》select * from bc_staff;
       //指定hibernate框架封装对象的方式
        detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        int firstResult = (currentPage - 1) * pageSize;
        int maxResults = pageSize;
        List rows = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
        pageBean.setRows(rows);
    }

    /**
     * 保存或者更新
     * @param entity
     */
    @Override
    public void saveOrUpdate(T entity) {
        getHibernateTemplate().saveOrUpdate(entity);
    }

    @Override
    public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
        return (List<T>) getHibernateTemplate().findByCriteria(detachedCriteria);
    }


}
