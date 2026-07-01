/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.OrderDetails;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.OrderDetailsPK;
import model.Orders;
import model.dao.exceptions.IllegalOrphanException;
import model.dao.exceptions.NonexistentEntityException;
import model.dao.exceptions.PreexistingEntityException;

/**
 *
 * @author wuann_
 */
public class OrdersDAO implements Serializable {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SE1925_VuongMinhQuan_SE204737_workshop2PU");

    public OrdersDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public OrdersDAO() {
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Orders orders) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            List<OrderDetails> details = orders.getOrderDetailsList();
            orders.setOrderDetailsList(new ArrayList<>());

            em.persist(orders);
            em.flush();

            if (details != null) {
                for (OrderDetails detail : details) {
                    if (detail.getOrderDetailsPK() == null) {
                        detail.setOrderDetailsPK(new OrderDetailsPK());
                    }
                    detail.getOrderDetailsPK().setOrderId(orders.getOrderId());
                    detail.getOrderDetailsPK().setProductId(detail.getProducts().getProductId());
                    detail.setOrders(orders);

                    em.persist(detail);
                }
            }

            orders.setOrderDetailsList(details);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            if (findOrders(orders.getOrderId()) != null) {
                throw new PreexistingEntityException("Orders " + orders + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Orders orders) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            orders = em.merge(orders);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = orders.getOrderId();
                if (findOrders(id) == null) {
                    throw new NonexistentEntityException("The orders with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orders orders;
            try {
                orders = em.getReference(Orders.class, id);
                orders.getOrderId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orders with id " + id + " no longer exists.", enfe);
            }
            em.remove(orders);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Orders> findOrdersEntities() {
        return findOrdersEntities(true, -1, -1);
    }

    public List<Orders> findOrdersEntities(int maxResults, int firstResult) {
        return findOrdersEntities(false, maxResults, firstResult);
    }

    private List<Orders> findOrdersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Orders.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Orders findOrders(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Orders.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Orders> rt = cq.from(Orders.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Orders> listAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT o FROM Orders o", Orders.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Orders findOrderWithDetails(int orderId) {
        EntityManager em = getEntityManager();
        try {
            Orders order = em.find(Orders.class, orderId);
            if (order != null && order.getOrderDetailsList() != null) {
                order.getOrderDetailsList().size();
                for (OrderDetails detail : order.getOrderDetailsList()) {
                    if (detail.getProducts() != null) {
                        detail.getProducts().getProductName();
                    }
                }
            }
            return order;
        } finally {
            em.close();
        }
    }

}
