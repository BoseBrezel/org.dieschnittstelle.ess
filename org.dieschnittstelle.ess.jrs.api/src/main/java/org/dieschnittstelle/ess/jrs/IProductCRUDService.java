package org.dieschnittstelle.ess.jrs;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;

import java.util.List;

/*
 * UE JRS2: 
 * deklarieren Sie hier Methoden fuer:
 * - die Erstellung eines Produkts
 * - das Auslesen aller Produkte
 * - das Auslesen eines Produkts
 * - die Aktualisierung eines Produkts
 * - das Loeschen eines Produkts
 * und machen Sie diese Methoden mittels JAX-RS Annotationen als WebService verfuegbar
 */

/*
 * TODO JRS3: aendern Sie Argument- und Rueckgabetypen der Methoden von IndividualisedProductItem auf AbstractProduct
 */
@Path("/products")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})

public interface IProductCRUDService
{
	//Get All
	@GET
	public List<AbstractProduct> readAllProducts();

	//Get by id
	@GET
	@Path("{iD}")
	public AbstractProduct readProduct( @PathParam("iD") long id);

	//Put
	@PUT
	@Path("{iD}")
	public AbstractProduct updateProduct( @PathParam("iD") long id, AbstractProduct update);

	//Delete
	@DELETE
	@Path("{iD}")
	boolean deleteProduct( @PathParam("iD") long id);

	//Post
	@POST
	public AbstractProduct createProduct(AbstractProduct prod);

}
