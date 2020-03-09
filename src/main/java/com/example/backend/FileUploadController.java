package com.example.backend;

import com.example.backend.storage.SimulationModel;
import com.example.backend.storage.SimulationResult;
import com.example.backend.storage.StorageFileNotFoundException;
import com.example.backend.storage.StorageService;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

		/*model.addAttribute("files", storageService.loadAll().map(
				path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
						"serveFile", path.getFileName().toString(),FOLDER).build().toString())
				.collect(Collectors.toList()));*/

        return "localhost:3000/simulator";
    }

    @CrossOrigin
    @GetMapping("/files/{folder}/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename, @PathVariable String folder) {

        Resource file = storageService.loadAsResource(folder,filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @ResponseBody
    @PostMapping(value = "/process",consumes = MediaType.APPLICATION_JSON_VALUE)
    public SimulationResult process(@RequestBody SimulationModel simulationModel) throws Exception {

        SimulationResult simulationResult = new SimulationResult();

        String generatedID = UUID.randomUUID().toString().substring(0,4); //generera nytt id

        String simulationFolder = storageService.initializeProjectFolder(generatedID).toString() + "/"; //skapa en folder med id som namn

        int nodes = Integer.parseInt(simulationModel.getNodes());

        Simulation.runSim(simulationFolder,nodes);

        simulationResult.setSimulationID(generatedID);

        return simulationResult;

    }

    @PostMapping("/")
    public SimulationResult handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        Path projectPath = storageService.store(file);
        String pathString = projectPath.toString()+ "/";
        //redirectAttributes.addFlashAttribute("message","You successfully uploaded " + file.getOriginalFilename() + " to the project folder " + pathString.substring(12,16) + "!");

        Simulation.runSim(file.getOriginalFilename(), pathString);
        SimulationResult simulationResult = new SimulationResult();
        simulationResult.setSimulationID(pathString.substring(12,16));
        return simulationResult;


    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
