package org.dieschnittstelle.ess.mip.components.erp.crud.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.dieschnittstelle.ess.entities.erp.StockItem;
import org.dieschnittstelle.ess.utils.interceptors.Logged;

import java.util.List;

@ApplicationScoped
@Transactional
@Logged
public class StockItemCRUDImpl  implements StockItemCRUD
{
    @Inject
    @EntityManagerProvider.ERPDataAccessor
    private EntityManager em;


    @Override
    public StockItem createStockItem(StockItem item)
    {
        em.persist(item);
        return item;
    }

    @Override
    public StockItem readStockItem(IndividualisedProductItem prod, PointOfSale pos)
    {
        Query query = em.createQuery("SELECT si FROM StockItem si WHERE si.product.id = :prodID AND si.pos.id = :posID");
        query.setParameter("prodID", prod.getId());
        query.setParameter("posID", pos.getId());
        try
        {
            return (StockItem) query.getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

    @Override
    public StockItem updateStockItem(StockItem item)
    {
        return em.merge(item);
    }

    @Override
    public List<StockItem> readStockItemsForProduct(IndividualisedProductItem prod) {
        Query query = em.createQuery("SELECT si FROM StockItem si WHERE si.product.id = :prodID");
        query.setParameter("prodID", prod.getId());
        return query.getResultList();
    }


    @Override
    public List<StockItem> readStockItemsForPointOfSale(PointOfSale pos)
    {
        Query query = em.createQuery("SELECT si FROM StockItem si WHERE si.pos.id = :posID");
        query.setParameter("posID", pos.getId());
        return query.getResultList();
    }
}
