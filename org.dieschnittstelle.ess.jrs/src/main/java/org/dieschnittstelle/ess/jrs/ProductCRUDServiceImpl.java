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

	@Context
	private ServletContext servletContext;

	private GenericCRUDExecutor<AbstractProduct> getExecFromServletContext()
	{
		return (GenericCRUDExecutor<AbstractProduct>) servletContext.getAttribute("productCRUD");
	}

	@Override
	public AbstractProduct createProduct(AbstractProduct prod)
	{
		return (AbstractProduct) getExecFromServletContext ().createObject(prod);
	}

	@Override
	public List<AbstractProduct> readAllProducts()
	{
		return (List)getExecFromServletContext().readAllObjects();
	}

	@Override
	public AbstractProduct updateProduct(long id, AbstractProduct update)
	{
		IndividualisedProductItem prod = (IndividualisedProductItem) getExecFromServletContext().readObject(id);
		if (prod != null)
		{
			return (AbstractProduct) getExecFromServletContext().updateObject(id, update);
		}
		else
		{
			return null;
		}
	}

	@Override
	public boolean deleteProduct(long id)
	{
		return getExecFromServletContext().deleteObject(id);
	}

	@Override
	public IndividualisedProductItem readProduct(long id)
	{
		IndividualisedProductItem prod = (IndividualisedProductItem) getExecFromServletContext().readObject(id);

		if (prod != null)
		{
			return prod;
		}
		else
		{
			throw new NotFoundException("The touchpoint with id " + id + " does not exist!");
		}
	}
	
}
