package ibf2022.batch2.csf.backend.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.repositories.ImageRepository;

@Controller
@RequestMapping(path = "/upload")
public class UploadController {
	@Autowired
	private ImageRepository imageRepository;

	// TODO: Task 2, Task 3, Task 4
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<List<String>> postUpload(
	@RequestPart String name, 
	@RequestPart String title,
	@RequestPart String comments, 
	@RequestPart MultipartFile file) throws IOException{

		List<String> uploadedFiles = imageRepository.upload(file);
    return ResponseEntity.ok(uploadedFiles);

		
	}
	
	

	// TODO: Task 5
	

	// TODO: Task 6

}
