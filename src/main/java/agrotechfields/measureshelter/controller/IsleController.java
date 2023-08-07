package agrotechfields.measureshelter.controller;

import java.util.List;
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
  public ResponseEntity<Isle> findIsleById(@PathVariable int isleId) {
    Isle isle = this.isleService.findIsleById(isleId);
    return ResponseEntity.status(HttpStatus.OK).body(isle);
  }

  @GetMapping("/is/{name}")
  public ResponseEntity<Isle> findIsleByName(@PathVariable String name) {
    Isle isle = this.isleService.findIsleByName(name);
    return ResponseEntity.status(HttpStatus.OK).body(isle);
  }

  @PostMapping
  public ResponseEntity<Isle> saveIsle(@RequestBody IsleDto isleDto) {
    Isle isle = this.isleService.saveIsle(isleDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(isle);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteIsle(@PathVariable int isleId) {
    this.isleService.deleteIsle(isleId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Isle> updateIsle(@PathVariable int isleId, @RequestBody IsleDto isleDto) {
    Isle isle = this.isleService.updateIsle(isleId, isleDto);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(isle);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Boolean> updateIsleStatus(@PathVariable int isleId,
      @RequestBody boolean status) {
    this.isleService.updateIsleStatus(isleId, status);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(status);
  }
}
