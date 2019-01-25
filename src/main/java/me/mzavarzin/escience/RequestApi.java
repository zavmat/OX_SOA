package me.mzavarzin.escience;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.json.JSONException;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * escience
 *
 * <p>eScience system. Created s part of the Oxford Software Engineering Program SOA Course assignment. Intended to model a scientific experiment replication service
 *
 */
@Component
@Path("/request")
public class RequestApi  {

    RequestBackend backend = new RequestBackend();


    /**
     * Create a new requests in the system - OK
     */
    @POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a new requests in the system", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Request created", content = @Content(schema = @Schema(implementation = Request.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request supplied") })
    public Response postRequest(String input, @Context UriInfo ui) throws JSONException, NotFoundException, IOException{
        String uuid = null; 
        try{
            uuid = backend.createRequest(input);
        } catch (JSONException je) {
            return Response.status(400).build(); 
        }
		URI location = ui.getAbsolutePathBuilder().path(uuid).build();
        String response = backend.getRequest(uuid).toString();
        return Response.created(location).entity(response).build();
    }

      /**
     * Returns all requests in the system
     */
    @GET
    @Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Returns all requests in the system", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "A list of Requests" , content = @Content(schema = @Schema(implementation = Request.class))) })
        
    public Response getRequests() throws IOException{
        String allRequests = backend.getRequests();
        System.out.println("All requests: "+allRequests);
        return Response.ok().entity(allRequests).build();
    }

    

    /**
     * Deletes an existing request
     *
     * Deletes and existing request
     * 
     * @return
     *
     */
    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletes an existing request", tags = {})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Request not found") })
    public Response deleteRequest(@PathParam("id") String id) {
        try {
			boolean deleted = backend.deleteRequest(id);
			if (deleted) {
				return Response.status(Status.OK).build();
            }
            else{
                throw new NotFoundException();
            }
		    } catch ( NotFoundException e) {
			    return Response.status(Status.NOT_FOUND).build();
            }
        }	
    

    /**
     * Returns a request by id -OK
     */
    @GET
    @Path("/{id}")
    @Produces({ "application/json" })
    @Operation(summary = "Returns a request by id", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Request found", content = @Content(schema = @Schema(implementation = Request.class))),
        @ApiResponse(responseCode = "404", description = "Request not found") })
    public Response getRequestById(@PathParam("id") String id) throws IOException{
        String requestJSON;
        try {
            requestJSON = backend.getRequest(id);
            // returns a JSON string based on a UUID
        } catch ( NotFoundException e) {
            return Response.status(404).build();
        } 
        return Response.ok().entity(requestJSON.toString()).build();
        }
        
    /**
     * Updates an existing request
     * 
     * @throws IOException
     * @throws NotFoundException
     * @throws JSONException
     */
    @PUT
    @Path("/{id}")
    @Produces({ "application/json" })
    @Operation(summary = "Updates an existing request", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request updated", content = @Content(schema = @Schema(implementation = Request.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request supplied"),
            @ApiResponse(responseCode = "404", description = "Request not found") })
    public Response putOrder(String input, @PathParam("id") String id)
            throws JSONException, NotFoundException, IOException {
        String requestJSON;
		try {
            System.out.println("Got this id:" + id);
			backend.updateRequest(id, input);
		// this updates an order - takes a UUID and a JSON string
		} catch (JSONException je) {
				return Response.status(Status.BAD_REQUEST).build();
		} catch (IOException e) {
                return Response.status(Status.BAD_REQUEST).build();    
        } catch (NotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();   
        }
        requestJSON = backend.getRequest(id);
        System.out.println(requestJSON.toString());
		return Response.ok().entity(requestJSON).build();
	}

    @GET
    @Path("/{id}/run")
    @Produces({ "application/json" })
    @Operation(summary = "Runs a request", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "202", description = "Request scheduled to run", content = @Content(schema = @Schema(implementation = Request.class))),
        @ApiResponse(responseCode = "404", description = "Request not found"),
        @ApiResponse(responseCode = "405", description = "Operation on this request is not allowed") })
    public Response runRequest(@PathParam("id") String id){
    
        return Response.ok().entity("TO-DO").build();
    }
}
