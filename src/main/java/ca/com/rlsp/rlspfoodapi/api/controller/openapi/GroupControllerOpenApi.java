package ca.com.rlsp.rlspfoodapi.api.controller.openapi;

import ca.com.rlsp.rlspfoodapi.api.exceptionhandler.ApiHandleProblemDetail;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.GroupInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.GroupOutputDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Api(tags = "Groups")
public interface GroupControllerOpenApi {

    @ApiOperation(value = "List all groups in JSON")
    public List<GroupOutputDto> listAll() ;

    @ApiOperation(value = "Get a Group by ID") // Costomize method description on SwaggerUI
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid group id",
                    content = @Content(schema = @Schema(implementation = ApiHandleProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Group not found",
                    content = @Content(schema = @Schema(implementation = ApiHandleProblemDetail.class)))
    })
    public GroupOutputDto findById(Long id);

    @ApiOperation(value = "Insert a group") // Costomize method description on SwaggerUI
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Group created",
                    content = @Content(schema = @Schema(implementation = ApiHandleProblemDetail.class)))
    })
    public GroupOutputDto save(GroupInputDto groupInput);

    @ApiOperation(value = "Update data of a group by ID") // Costomize method description on SwaggerUI
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Group updated",
                    content = @Content(schema = @Schema(implementation = ApiHandleProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Group not found",
                    content = @Content(schema = @Schema(implementation = ApiHandleProblemDetail.class)))
    })
    public GroupOutputDto update(Long id, GroupInputDto groupInputDto);

    @ApiOperation("Remove a group")  // Customize method description on SwaggerUI
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Group removed",
                    content = @Content(schema = @Schema(implementation = ApiHandleProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Group not found",
                    content = @Content(schema = @Schema(implementation = ApiHandleProblemDetail.class)))
    })
    public void remove(Long id);
}
