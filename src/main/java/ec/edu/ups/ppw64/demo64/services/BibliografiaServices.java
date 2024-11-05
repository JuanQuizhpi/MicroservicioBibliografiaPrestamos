package ec.edu.ups.ppw64.demo64.services;

import java.util.List;

import ec.edu.ups.ppw64.demo64.business.GestionBibliografia;
import ec.edu.ups.ppw64.demo64.model.Bibliografia;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("bibliografias")
public class BibliografiaServices {
	
	private final Tracer tracer = GlobalTracer.get();
	
	@Inject
	private GestionBibliografia gBibliografias;
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response guardarBibliografia(Bibliografia bibliografia) {
		Span spanGuardarBibliografia = tracer.buildSpan("Creacion Bibliografia").start();
		System.out.println(bibliografia);
		try{
			gBibliografias.guardarBibliografia(bibliografia);
			ErrorMessage error = new ErrorMessage(1, "OK");
			return Response.status(Response.Status.CREATED)
					.entity(error)
					.build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.build();
		} finally {
			spanGuardarBibliografia.finish();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizarBibliografia(Bibliografia bibliografia) {
		Span spanActualizarBibliografia = tracer.buildSpan("Actualizacion de Bibliografia").start();
		try{
			gBibliografias.actualizarBibliografia(bibliografia);
			return Response.ok(bibliografia).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}finally {
			spanActualizarBibliografia.finish();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarBibliografia(@QueryParam("idCurso") int idCurso) {
		Span spanEliminarBibliografia = tracer.buildSpan("Eliminar Bibliografia").start();
		try{
			this.gBibliografias.borrarBibliografia(idCurso);
			ErrorMessage error = new ErrorMessage(1, "OK");
			return Response.status(Response.Status.CREATED)
					.entity(error)
					.build();
		}catch (Exception e) {
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.build();
		} finally {
			spanEliminarBibliografia.finish();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBibliografiaPorID(@QueryParam("idCurso") int idCurso) {
		Span spanGetBibliografiaPorID = tracer.buildSpan("Bibliografia por ID").start();
		try{
			System.out.println("idCurso" +  idCurso);
			Bibliografia bibliografia = gBibliografias.getBibliografiaPorID(idCurso);
			return Response.ok(bibliografia).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(4, "No hay bibliografia");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}finally {
			spanGetBibliografiaPorID.finish();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getBibliografias(){
		Span spanGetBibliografias = tracer.buildSpan("Obtener Bibliografias").start();
		
		try {
			List<Bibliografia> bibliografias = gBibliografias.getBibliografia();
			if(bibliografias.size()>0) {
				return Response.ok(bibliografias).build();
			} else {
				ErrorMessage error = new ErrorMessage(6, "No se registran bibliografias");
				return Response.status(Response.Status.NOT_FOUND)
						.entity(error)
						.build();
			}
		} catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(6, "No se registran bibliografias");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		} finally {
			spanGetBibliografias.finish();
		}

	}
	

}
