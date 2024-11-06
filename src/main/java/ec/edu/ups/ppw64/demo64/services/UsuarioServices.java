package ec.edu.ups.ppw64.demo64.services;

import java.util.List;

import ec.edu.ups.ppw64.demo64.business.GestionUsuario;
import ec.edu.ups.ppw64.demo64.model.Usuario;
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

@Path("usuarios")
public class UsuarioServices {

	private final Tracer tracer = GlobalTracer.get();
	
	@Inject
	private GestionUsuario gUsuarios;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response guardarUsuario(Usuario usuario) {
		Span spanGuardarUsuario = tracer.buildSpan("Creacion Usuario").start();
		System.out.println(usuario);
		try{
			gUsuarios.guardarUsuario(usuario);
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
			spanGuardarUsuario.finish();
		}
	}
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizarUsuario(Usuario usuario) {
		Span spanActualizarUsuario = tracer.buildSpan("Actualizacion de Usuario").start();
		try{
			gUsuarios.actualizarUsuario(usuario);
			return Response.ok(usuario).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}finally {
			spanActualizarUsuario.finish();
		}
	}
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarUsuario(@QueryParam("cedula") String cedula) {
		Span spanEliminarUsuario = tracer.buildSpan("Eliminar Usuario").start();
		try{
			this.gUsuarios.borrarUsuario(cedula);
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
			spanEliminarUsuario.finish();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuarioPorCedula(@QueryParam("cedula") String cedula) {
		Span spanGetUsuarioPorCedula = tracer.buildSpan("Usuario por Cedula").start();
		try{
			System.out.println("cedula" +  cedula);
			Usuario usuario = gUsuarios.getUsuarioPorCedula(cedula);
			return Response.ok(usuario).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(4, "No hay usuario");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}finally {
			spanGetUsuarioPorCedula.finish();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getUsuarios(){
		Span spanGetUsuarios = tracer.buildSpan("Obtener Usuarios").start();
		
		try {
			List<Usuario> usuarios = gUsuarios.getUsuario();
			if(usuarios.size()>0) {
				return Response.ok(usuarios).build();
			} else {
				ErrorMessage error = new ErrorMessage(6, "No se registran usuarios");
				return Response.status(Response.Status.NOT_FOUND)
						.entity(error)
						.build();
			}
		} catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(6, "No se registran usuarios");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		} finally {
			spanGetUsuarios.finish();
		}

	}
}
