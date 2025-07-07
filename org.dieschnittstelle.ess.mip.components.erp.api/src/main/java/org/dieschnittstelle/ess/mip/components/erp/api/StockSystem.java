package org.dieschnittstelle.ess.mip.components.erp.api;

import java.util.List;

import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.StockItem;

public interface StockSystem
{

	/**
	 * adds some units of a product to the stock of a point of sale
	 *
	 * should check whether stock item for the given combination of product and pointOfSale already exists.
	 * Depending on this, the existing stock item will be updated or a new one will be created
	 *
	 * @param product
	 * @param pointOfSaleId
	 * @param units
	 */
	void addToStock(IndividualisedProductItem product, long pointOfSaleId, int units);

	/**
	 * removes some units of a product from the stock of a point of sale
	 *
	 * here you can reuse the addToStock method passing a negative value for units
	 *
	 * @param product
	 * @param pointOfSaleId
	 * @param units
	 */
	void removeFromStock(IndividualisedProductItem product, long pointOfSaleId, int units);

	/**
	 * returns all products on stock of some pointOfSale
	 *
	 * this method can be implemented on the basis of the readStockItemsForPointOfSale() method
	 * in the CRUD Bean for StockItem, note that here and in most of the following methods you
	 * first need to read out the PointOfSale object for the given pointOfSaleId
	 *
	 * @param pointOfSaleId
	 * @return
	 */
	List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId);

	/**
	 * returns all products on stock
	 *
	 * this method can be implemented using the readAllPointsOfSale() method from the PointOfSaleCRUD
	 * Bean and using getProductsOnStock for each of point of sale. Note that there should be
	 * no duplicates in the list that is returned.
	 *
	 * @return
	 */
	List<IndividualisedProductItem> getAllProductsOnStock();

	/**
	 * returns the units on stock for a product at some point of sale
	 *
	 * this method can very easily be implemented using the readStockItem method of the CRUD Bean for StockItem.
	 * Consider the case that no stock item exists for the given combination to avoid NullPointerException
	 *
	 * @param product
	 * @param pointOfSaleId
	 * @return
	 */
	int getUnitsOnStock(IndividualisedProductItem product, long pointOfSaleId);

	/**
	 * returns the total number of units on stock for some product
	 *
	 * here you can use readStockItemsForProduct() and sum up the units
	 *
	 * @param product
	 * @return
	 */
	int getTotalUnitsOnStock(IndividualisedProductItem product);

	/**
	 * returns the points of sale where some product is available
	 *
	 * here you can use readStockItemsForProduct() and create a list of the stock items'
	 * pointOfSale Ids
	 *
	 * @param product
	 * @return
	 */
	List<Long> getPointsOfSale(IndividualisedProductItem product);


}
