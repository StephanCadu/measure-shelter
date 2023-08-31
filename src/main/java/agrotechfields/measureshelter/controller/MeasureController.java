package agrotechfields.measureshelter.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
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
import agrotechfields.measureshelter.dto.MeasureResponse;
import agrotechfields.measureshelter.model.Measure;
import agrotechfields.measureshelter.service.MeasureService;

@RestController
@RequestMapping("/measures")
public class MeasureController {

  @Autowired
  private MeasureService measureService;

  @GetMapping
  public ResponseEntity<List<MeasureResponse>> findMeasures() {
    List<MeasureResponse> measures = this.measureService.findMeasures().stream()
        .map(measure -> new MeasureResponse(measure)).collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(measures);
  }

  @GetMapping("/{id}")
  public ResponseEntity<MeasureResponse> findMeasureById(@PathVariable("id") String measureId) {
    Measure measure = this.measureService.findMeasureById(new ObjectId(measureId));
    return ResponseEntity.status(HttpStatus.OK).body(new MeasureResponse(measure));
  }

  @GetMapping("/byisle/{id}")
  public ResponseEntity<List<MeasureResponse>> findMeasuresByIsle(
      @PathVariable("id") String isleId) {

    List<MeasureResponse> measures = this.measureService.findMeasuresByIsle(isleId).stream()
        .map(measure -> new MeasureResponse(measure)).collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(measures);
  }

  @PostMapping
  public ResponseEntity<MeasureResponse> saveMeasure(@Valid @RequestBody MeasureDto measureDto) {
    Measure measure = this.measureService.saveMeasure(measureDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(new MeasureResponse(measure));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteMeasure(@PathVariable("id") String measureId) {
    this.measureService.deleteMeasure(new ObjectId(measureId));
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<MeasureResponse> updateMeasure(@PathVariable("id") String measureId,
      @Valid @RequestBody MeasureDto measureDto) {
    Measure measure = this.measureService.updateMeasure(new ObjectId(measureId), measureDto);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MeasureResponse(measure));
  }
}
