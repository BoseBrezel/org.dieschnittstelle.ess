package org.dieschnittstelle.ess.mip.components.erp.crud.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Path;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.mip.components.erp.api.StockSystem;
import org.dieschnittstelle.ess.mip.components.erp.api.StockSystemService;
import org.dieschnittstelle.ess.mip.components.erp.crud.api.ProductCRUD;
import org.dieschnittstelle.ess.utils.interceptors.Logged;

import java.util.List;

@ApplicationScoped
@Transactional
@Logged
public class StockSystemServiceImpl implements StockSystemService
{
    @Inject
    private StockSystem stockSystem;

    @Inject
    private ProductCRUD productCRUD;

    @Override
    public void addToStock(long productId, long pointOfSaleId, int units)
    {
        IndividualisedProductItem product = (IndividualisedProductItem) productCRUD.readProduct(productId);
        stockSystem.addToStock(product, pointOfSaleId, units);
    }

    @Override
    public void removeFromStock(long productId, long pointOfSaleId, int units)
    {
        IndividualisedProductItem product = (IndividualisedProductItem) productCRUD.readProduct(productId);
        stockSystem.removeFromStock(product, pointOfSaleId, units);
    }

    @Override
    public List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId)
    {
        if(pointOfSaleId == 0)
        {
            return stockSystem.getAllProductsOnStock();
        }
        else
        {
            return stockSystem.getProductsOnStock(pointOfSaleId);

        }
    }

    @Override
    public int getUnitsOnStock(long productId, long pointOfSaleId)
    {
        IndividualisedProductItem product = (IndividualisedProductItem) productCRUD.readProduct(productId);
        if(pointOfSaleId == 0)
        {
            return stockSystem.getTotalUnitsOnStock(product);
        }
        else
        {
            return stockSystem.getUnitsOnStock(product , pointOfSaleId);
        }
    }

    @Override
    public List<Long> getPointsOfSale(long productId)
    {
        IndividualisedProductItem product = (IndividualisedProductItem) productCRUD.readProduct(productId);
        return stockSystem.getPointsOfSale(product);
    }
}
