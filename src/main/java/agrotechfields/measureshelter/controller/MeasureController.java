package agrotechfields.measureshelter.controller;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import agrotechfields.measureshelter.dto.MeasureDto;
import agrotechfields.measureshelter.model.Measure;
import agrotechfields.measureshelter.service.MeasureService;

@RestController
@RequestMapping("/measures")
public class MeasureController {

  @Autowired
  private MeasureService measureService;

  @GetMapping
  public ResponseEntity<List<Measure>> findMeasures() {
    return ResponseEntity.status(HttpStatus.OK).body(this.measureService.findMeasures());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Measure> findMeasureById(@PathVariable("id") String measureId) {
    Measure measure = this.measureService.findMeasureById(new ObjectId(measureId));
    return ResponseEntity.status(HttpStatus.OK).body(measure);
  }

  @GetMapping("/byisle/{id}")
  public ResponseEntity<List<Measure>> findMeasuresByIsle(@PathVariable("id") int isleId) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(this.measureService.findMeasuresByIsle(isleId));
  }

  @PostMapping
  public ResponseEntity<Measure> saveMeasure(@RequestBody MeasureDto measureDto) {
    Measure measure = this.measureService.saveMeasure(measureDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(measure);
  }

  @DeleteMapping("/{id}")
  @RolesAllowed("ADMIN")
  public ResponseEntity<String> deleteMeasure(@PathVariable("id") String measureId) {
    this.measureService.deleteMeasure(new ObjectId(measureId));
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PutMapping("/{id}")
  @RolesAllowed("ADMIN")
  public ResponseEntity<Measure> updateMeasure(@PathVariable("id") String measureId,
      @RequestBody MeasureDto measureDto) {
    Measure measure = this.measureService.updateMeasure(new ObjectId(measureId), measureDto);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(measure);
  }
}
