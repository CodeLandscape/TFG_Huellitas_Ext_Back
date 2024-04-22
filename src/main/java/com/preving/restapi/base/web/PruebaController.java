package com.preving.restapi.base.web;

import com.preving.restapi.base.domain.Entidad;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "prueba")
public class PruebaController {

    // todo ejemplos de documentacion de swagger

    @Operation(summary = "Actualizar una Entidad",
            description = "Actualizar el objeto Entidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Peticion realizada correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Entidad.class))),
            @ApiResponse(responseCode = "404", description = "Entidad no encontrada")}
    )
    @PostMapping(value = "/previngPost", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Entidad rogePost(HttpServletRequest request) {
        return new Entidad(1, "Preving", true);
    }

    @Operation(summary = "Obtener una Entidad",
            description = "Retorna el objeto Entidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Peticion realizada correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Entidad.class))),
            @ApiResponse(responseCode = "404", description = "Entidad no encontrada")}
    )
    @GetMapping(value = "/previngGet/{EntidadId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Entidad rogeGet(HttpServletRequest request,
                                            @Parameter(name="EntidadId", description = "Id de la Entidad para obtener los datos de este", required = true) @PathVariable("EntidadId") int EntidadId){
        return new Entidad(1, "Preving", true);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Peticion realizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Entidad no encontrada")}
    )
    @DeleteMapping (value = "/previngDelete/{EntidadId}")
    public @ResponseBody void rogeDelete(HttpServletRequest request,
                                         @Parameter(name="EntidadId", description = "Id de la Entidad para borrar", required = true) @PathVariable("EntidadId") int EntidadId){
    }
}
