package org.dieschnittstelle.ess.mip.components.erp.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;

import javax.print.attribute.standard.Media;
import java.util.List;

/**
 * TODO MIP3/4/6:
 * - declare the web api for this interface using JAX-RS
 * - implement the interface as a CDI Bean
 * - in the Bean implementation, delegate method invocations to the corresponding methods of the StockSystem Bean
 * - let the StockSystemClient in the client project access the web api via this interface - see ShoppingCartClient for an example
 */
@Path("/stock/pointsOfSale")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface StockSystemService {

	/**
	 * adds some units of a product to the stock of a point of sale
	 */
	@POST
	@Path("/{pointOfSaleId}/products/{productId}")
    void addToStock(@PathParam("productId") long productId,  @PathParam("pointOfSaleId") long pointOfSaleId, @QueryParam("units") int units);

	/**
	 * removes some units of a product from the stock of a point of sale
	 */
	@PATCH
	@Path("/{pointOfSaleId}/products/{productId}")
    void removeFromStock(@PathParam("productId") long productId, @PathParam("pointOfSaleId") long pointOfSaleId, @QueryParam("units") int units);

	/**
	 * returns all products on stock or, if pointOfSaleId is specified, the products for some pointOfSale
	 */
	@GET
	@Path("{pointOfSaleId}/products")
    List<IndividualisedProductItem> getProductsOnStock(@PathParam("pointOfSaleId") long pointOfSaleId);

	/**
	 * returns the units on stock for a given product overall or, if a pointOfSaleId is specified, at some point of sale
	 */
	@GET
	@Path("/{pointOfSaleId}/products/{productId}")
    int getUnitsOnStock(@PathParam("productId") long productId, @PathParam("pointOfSaleId")  long pointOfSaleId);

	/**
	 * returns the points of sale where some product is available
	 */
	@GET
    List<Long> getPointsOfSale(@QueryParam("productId") long productId);

}
