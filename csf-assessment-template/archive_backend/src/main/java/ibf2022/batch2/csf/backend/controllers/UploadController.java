package ibf2022.batch2.csf.backend.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.models.Archive;
import ibf2022.batch2.csf.backend.repositories.ArchiveRepository;
import ibf2022.batch2.csf.backend.repositories.ImageRepository;

@Controller
@RequestMapping(path = "/upload")
public class UploadController {

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private ArchiveRepository archiveRepository;

	// TODO: Task 2, Task 3, Task 4
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> postUpload(
			@RequestPart String name,
			@RequestPart String title,
			@RequestPart String comments,
			@RequestPart MultipartFile file) throws IOException {

		try {
			List<String> uploadedFiles = imageRepository.upload(file);
			Archive archive = archiveRepository.recordBundle(name, title, comments, uploadedFiles);
			return ResponseEntity.status(HttpStatus.CREATED).body("{\"bundleId\":\"" + archive.getBundleId() + "\"}");
		} catch (Exception e) {
			String errorMessage = "Failed to process the file: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"" + errorMessage + "\"}");
		}
	}

	// TODO: Task 5

	@GetMapping(path = "/bundleId/{bundleId}")
	public ResponseEntity<Archive> getBundleByBundleId(@PathVariable String bundleId) {
		try {
			Archive archive = archiveRepository.getBundleByBundleId(bundleId);
			if (archive != null) {
				return ResponseEntity.ok(archive);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	// TODO: Task 6
	@GetMapping(path = "/bundles")
	public ResponseEntity<List<Archive>> getBundles() {
		try {
			List<Archive> archive = archiveRepository.getBundles();
			return ResponseEntity.ok(archive);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}