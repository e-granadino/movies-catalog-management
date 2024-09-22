package com.ravn.challenge.movies_catalog_management.controller;

import com.ravn.challenge.movies_catalog_management.service.StorageService;
import com.ravn.challenge.movies_catalog_management.utils.Constants;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/storage")
@Tag(name = "Storage Management", description = "API for managing file storage")
public class StorageController {

    @Operation(summary = "Upload Image", description = "Allows to upload an image that will use as movie poster, the image is store in a external system")
    @ApiResponse(responseCode = "200", description = "The image was upload successfully", content = {@Content(mediaType = "application/json")})
    @ApiResponse(responseCode = "500", description = "There was an error while uploading the image")
    @PostMapping("uploadImage")
    public GenericRestResponse<String> uploadFile(@RequestParam("image") MultipartFile file) {
        GenericRestResponse<String> response = new GenericRestResponse<>();
        try {
            String fileName = file.getOriginalFilename();
            String url = storageService.uploadFile(file, fileName);
            response.setResponse(url);
            response.setMessage("The image was uploaded successfully");
            response.setStatus(Constants.SUCCESS_RESPONSE);
            response.setStatusCode(HttpStatus.OK.value());

            return response;
        } catch (Exception e) {
            return new GenericRestResponse.Builder<String>()
                    .message(e.getMessage())
                    .status(Constants.FAIL_RESPONSE)
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    private final StorageService storageService;
}
