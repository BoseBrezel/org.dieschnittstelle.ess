package org.dieschnittstelle.ess.jrs;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import org.dieschnittstelle.ess.entities.GenericCRUDExecutor;
import org.dieschnittstelle.ess.entities.crm.StationaryTouchpoint;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;

import java.util.List;

import static org.dieschnittstelle.ess.utils.Utils.show;

/*
 * TODO JRS2: implementieren Sie hier die im Interface deklarierten Methoden
 */

public class ProductCRUDServiceImpl implements IProductCRUDService {


	//Frage ist das richtig?
	@Context
	private ServletContext servletContext;
	private GenericCRUDExecutor<IndividualisedProductItem> productCRUD;

	//Frage ist IndividualLisedProdictItem richtig?
	public ProductCRUDServiceImpl(@Context ServletContext servletContext) {
		// read out the dataAccessor
		this.productCRUD = (GenericCRUDExecutor<IndividualisedProductItem>) servletContext.getAttribute("productCRUD");
	}

	@Override
	public IndividualisedProductItem createProduct(IndividualisedProductItem prod)
	{
		return (IndividualisedProductItem) this.productCRUD.createObject(prod);
	}

	@Override
	public List<IndividualisedProductItem> readAllProducts()
	{
		return (List) this.productCRUD.readAllObjects();
	}

	@Override
	public IndividualisedProductItem updateProduct(long id, IndividualisedProductItem update)
	{
		IndividualisedProductItem prod = (IndividualisedProductItem) this.productCRUD.readObject(id);
		if (prod != null)
		{
			return this.productCRUD.updateObject(id, update);
		}
		else
		{
			throw new NotFoundException("The product with id " + id + " does not exist!");
		}
	}

	@Override
	public boolean deleteProduct(long id)
	{
		return this.productCRUD.deleteObject(id);
	}

	@Override
	public IndividualisedProductItem readProduct(long id)
	{
		IndividualisedProductItem prod = (IndividualisedProductItem) this.productCRUD.readObject(id);

		if (prod != null)
		{
			return prod;
		}
		else
		{
			throw new NotFoundException("The product with id " + id + " does not exist!");
		}
	}
	
}
