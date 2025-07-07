package org.dieschnittstelle.ess.mip.components.erp.crud.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.dieschnittstelle.ess.entities.erp.StockItem;
import org.dieschnittstelle.ess.mip.components.erp.api.StockSystem;
import org.dieschnittstelle.ess.mip.components.erp.crud.api.PointOfSaleCRUD;
import org.dieschnittstelle.ess.utils.interceptors.Logged;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
@Logged
public class StockSystemImpl implements StockSystem {

    @Inject
    private PointOfSaleCRUD posCRUD;

    @Inject
    private StockItemCRUD stockItemCRUD;

    @Override
    public void addToStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        StockItem stockItem = stockItemCRUD.readStockItem(product, pos);

        // 🛠️ Änderung: StockItem wird jetzt erstellt, falls er noch nicht existiert
        if (stockItem != null) {
            stockItem.setUnits(stockItem.getUnits() + units);
        } else {
            stockItem = new StockItem(product, pos, units); // neu: StockItem initialisieren
        }
        stockItemCRUD.createStockItem(stockItem); // bestehend oder neu, immer abspeichern
    }

    @Override
    public void removeFromStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        // 🛠️ Änderung: Vereinfachung durch Wiederverwendung von addToStock() mit negativem Wert
        addToStock(product, pointOfSaleId, -units);
    }

    @Override
    public List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        if (pos == null) {
            return List.of();
        }

        List<StockItem> stocks = stockItemCRUD.readStockItemsForPointOfSale(pos);
        return stocks.stream()
                .map(StockItem::getProduct)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<IndividualisedProductItem> getAllProductsOnStock() {
        List<PointOfSale> posList = posCRUD.readAllPointsOfSale();
        if (posList == null || posList.isEmpty()) {
            return List.of();
        }

        List<IndividualisedProductItem> noDuplicates = new ArrayList<>();

        for (PointOfSale pos : posList) {
            List<IndividualisedProductItem> products = getProductsOnStock(pos.getId());
            if (products != null) {
                for (IndividualisedProductItem product : products) {
                    if (!noDuplicates.contains(product)) {
                        noDuplicates.add(product);
                    }
                }
            }
        }

        return noDuplicates;
    }

    @Override
    public int getUnitsOnStock(IndividualisedProductItem product, long pointOfSaleId) {
        // 🛠️ Lesbarkeit verbessert: Trennung der Schritte
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        StockItem stockItem = stockItemCRUD.readStockItem(product, pos);
        return stockItem != null ? stockItem.getUnits() : 0;
    }

    @Override
    public int getTotalUnitsOnStock(IndividualisedProductItem product) {
        List<StockItem> items = stockItemCRUD.readStockItemsForProduct(product);
        if (items == null || items.isEmpty()) {
            return 0;
        }
        int itemCount = 0;
        for (StockItem item : items) {
            itemCount += item.getUnits();
        }
        return itemCount;
    }

    @Override
    public List<Long> getPointsOfSale(IndividualisedProductItem product) {
        List<StockItem> stockItems = stockItemCRUD.readStockItemsForProduct(product);
        List<Long> pointOfSaleIds = new ArrayList<>();

        for (StockItem item : stockItems) {
            Long id = item.getPos().getId();
            if (!pointOfSaleIds.contains(id)) {
                pointOfSaleIds.add(id);
            }
        }

        return pointOfSaleIds;
    }
}
