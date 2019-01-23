package me.mzavarzin.escience;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


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
@Path("/request")
public class RequestApi  {

    /**
     * Deletes an existing request
     *
     * Deletes and existing request
     *
     */
    @DELETE
    @Path("/request/{id}")
    @Operation(summary = "Deletes an existing request", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "Request not found") })
    public void deleteOrder(@PathParam("id") String id){

    }

    /**
     * Returns a request by id
     *
     * Returns a request by id
     *
     */
    @GET
    @Path("/request/{id}")
    @Produces({ "application/json" })
    @Operation(summary = "Returns a request by id", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Request found", content = @Content(schema = @Schema(implementation = Request.class))),
        @ApiResponse(responseCode = "404", description = "Request not found") })
    public Response getRequestById(@PathParam("id") String id){
        return Response.ok().entity("TO-DO").build();
    }

    /**
     * Returns all requests in the system
     *
     * Returns all requests in the system
     *
     */
    @GET
    @Path("/request")
    @Produces({ "application/json" })
    @Operation(summary = "Returns all requests in the system", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "A list of Requests", content = @Content(schema = @Schema(implementation = Requests.class))) })
    public Response getRequests(){
        return Response.ok().entity("TO-DO").build();
    }

    /**
     * Create a new requests in the system
     *
     * Create a new request in the system
     *
     */
    @POST
    @Path("/request")
    @Produces({ "application/json" })
    @Operation(summary = "Create a new requests in the system", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Request created", content = @Content(schema = @Schema(implementation = Request.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request supplied") })
    public Response postRequest(){
        return Response.ok().entity("TO-DO").build();
    }

    /**
     * Updates an existing request
     *
     * Updates and existing request
     *
     */
    @PUT
    @Path("/request/{id}")
    @Produces({ "application/json" })
    @Operation(summary = "Updates an existing request", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Request updated", content = @Content(schema = @Schema(implementation = Request.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request supplied"),
        @ApiResponse(responseCode = "404", description = "Request not found") })
    public Response putOrder(@PathParam("id") String id){
    
        return Response.ok().entity("TO-DO").build();
    }

    /**
     * Runs a request
     *
     * Runs a request
     *
     */
    @GET
    @Path("/request/{id}/run")
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
