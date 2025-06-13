package org.dieschnittstelle.ess.mip.components.erp.crud.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.Campaign;
import org.dieschnittstelle.ess.mip.components.erp.crud.api.ProductCRUD;
import org.dieschnittstelle.ess.utils.interceptors.Logged;

import java.util.List;
import java.util.Queue;

import static org.dieschnittstelle.ess.utils.Utils.show;

@ApplicationScoped
@Transactional
@Logged
public class ProductCRUDImpl implements ProductCRUD
{

    public ProductCRUDImpl(){
        show("constructor(): ProductsCRUDImpl");
    }

    @Inject
    @EntityManagerProvider.ERPDataAccessor
    private EntityManager em;

    @Override
    public AbstractProduct createProduct(AbstractProduct prod){
        show("constructor(): $s", prod);
        em.persist(prod);
        return prod;
    }

    @Override
    public List<AbstractProduct> readAllProducts(){
        show("readAllProducts()");
        Query qu = em.createQuery("select p from AbstractProduct p");
        return qu.getResultList();
    }

    @Override
    public AbstractProduct updateProduct(AbstractProduct update)
    {
        show("updateProduct()");
        return update;
    }

    @Override
    public AbstractProduct readProduct(long productID)
    {
        show("readProduct()");
        AbstractProduct prod = em.find(AbstractProduct.class, productID);
        return prod;
    }

    @Override
    public boolean deleteProduct(long productID)
    {
        show("deleteProduct()");
        return false;
    }

    @Override
    public List<Campaign> getCampaignsForProduct(long productID)
    {
        show("getCampaignsForProduct()");
        return List.of();
    }
}
