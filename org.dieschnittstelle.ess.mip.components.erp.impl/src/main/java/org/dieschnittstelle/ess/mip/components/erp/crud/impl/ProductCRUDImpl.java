package org.dieschnittstelle.ess.mip.components.erp.crud.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.Campaign;
import org.dieschnittstelle.ess.mip.components.erp.crud.api.ProductCRUD;

import java.util.List;

import static org.dieschnittstelle.ess.utils.Utils.show;

@ApplicationScoped
public class ProductCRUDImpl implements ProductCRUD
{

    public ProductCRUDImpl(){
        show("constructor(): ProductsCRUDImpl");
    }
    @Override
    public AbstractProduct createProduct(AbstractProduct prod){
        show("constructor(): $s", prod);
        return null;
    }

    @Override
    public List<AbstractProduct> readAllProducts(){
        show("readAllProducts()");
        return List.of();
    }

    @Override
    public AbstractProduct updateProduct(AbstractProduct update)
    {
        show("updateProduct()");
        return null;
    }

    @Override
    public AbstractProduct readProduct(long productID)
    {
        show("readProduct()");
        return null;
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
