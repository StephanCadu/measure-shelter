package agrotechfields.measureshelter.controller;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import agrotechfields.measureshelter.dto.IsleDto;
import agrotechfields.measureshelter.dto.IsleResponse;
import agrotechfields.measureshelter.model.Isle;
import agrotechfields.measureshelter.service.IsleService;

@RestController
@RequestMapping("/isle")
public class IsleController {

  @Autowired
  private IsleService isleService;

  @GetMapping
  public ResponseEntity<List<Isle>> findIsles() {
    return ResponseEntity.status(HttpStatus.OK).body(this.isleService.findIsles());
  }

  @GetMapping("/{id}")
  public ResponseEntity<IsleResponse> findIsleById(@PathVariable("id") String isleId) {
    Isle isle = this.isleService.findIsleById(new ObjectId(isleId));
    return ResponseEntity.status(HttpStatus.OK).body(new IsleResponse(isle));
  }

  @GetMapping("/is/{name}")
  public ResponseEntity<IsleResponse> findIsleByName(@PathVariable("name") String name) {
    Isle isle = this.isleService.findIsleByName(name);
    return ResponseEntity.status(HttpStatus.OK).body(new IsleResponse(isle));
  }

  @PostMapping
  public ResponseEntity<IsleResponse> saveIsle(@Valid @RequestBody IsleDto isleDto) {
    Isle isle = this.isleService.saveIsle(isleDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(new IsleResponse(isle));
  }

  @DeleteMapping("/{id}")
  @RolesAllowed("ADMIN")
  public ResponseEntity<String> deleteIsle(@PathVariable("id") String isleId) {
    this.isleService.deleteIsle(new ObjectId(isleId));
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PutMapping("/{id}")
  @RolesAllowed("ADMIN")
  public ResponseEntity<IsleResponse> updateIsle(@PathVariable("id") String isleId,
      @Valid @RequestBody IsleDto isleDto) {
    Isle isle = this.isleService.updateIsle(new ObjectId(isleId), isleDto);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(new IsleResponse(isle));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Boolean> updateIsleStatus(@PathVariable("id") String isleId,
      @RequestBody boolean status) {
    this.isleService.updateIsleStatus(new ObjectId(isleId), status);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(status);
  }
}
