package agrotechfields.measureshelter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import agrotechfields.measureshelter.dto.LoginDto;
import agrotechfields.measureshelter.dto.MeasureDto;
import agrotechfields.measureshelter.dto.UserDto;
import agrotechfields.measureshelter.model.Isle;
import agrotechfields.measureshelter.model.Measure;
import agrotechfields.measureshelter.model.User;
import agrotechfields.measureshelter.repository.IsleRepository;
import agrotechfields.measureshelter.repository.MeasureRepository;
import agrotechfields.measureshelter.repository.UserRepository;
import agrotechfields.measureshelter.utils.Direction;
import agrotechfields.measureshelter.utils.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
@ContextConfiguration(classes = MeasureShelterApplication.class)
@AutoConfigureMockMvc(addFilters = false)
class MeasureShelterApplicationTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private IsleRepository isleRepository;

  @Autowired
  private MeasureRepository measureRepository;

  private static String token;

  private static final HttpHeaders httpHeaders = new HttpHeaders();

  @BeforeEach
  public void cleanUsers() {
    userRepository.deleteAll();
  }

  public void mockLoginToken(String username, String password) throws Exception {
    LoginDto loginDto = new LoginDto(username, password);

    String body = objectMapper.writeValueAsString(loginDto);

    MvcResult result =
        mockMvc.perform(post("/users/login").contentType(MediaType.APPLICATION_JSON).content(body))
            .andReturn();

    String content = result.getResponse().getContentAsString();

    token = JsonPath.parse(content).read("$.token").toString();

    httpHeaders.setBearerAuth(token);
  }

  public String insertUser() {
    User user = new User(null, "username", passwordEncoder.encode("password"), Role.ADMIN);

    User savedUser = userRepository.insert(user);

    return savedUser.getId().toString();
  }

  public String insertIsle() {
    isleRepository.deleteAll();

    Isle isle = new Isle(null, "TEST_ISLE", 10, 10, 10, true);

    Isle savedIsle = isleRepository.insert(isle);

    return savedIsle.getId().toString();
  }

  public String insertMeasure(String isleId) {
    measureRepository.deleteAll();

    Measure measure =
        new Measure(null, new ObjectId(isleId), 10, 10, 10, 10, 10, 10, 10, Direction.WEST);

    Measure savedMeasure = measureRepository.insert(measure);

    return savedMeasure.getId().toString();
  }

  // ---------- USER TESTS ----------

  @Test
  void mustLogin() throws Exception {
    insertUser();

    LoginDto loginDto = new LoginDto("username", "password");

    String body = objectMapper.writeValueAsString(loginDto);

    mockMvc.perform(post("/users/login").contentType(MediaType.APPLICATION_JSON).content(body))
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.token").isNotEmpty());
  }

  @Test
  void mustFindAllUsers() throws Exception {
    insertUser();

    mockMvc.perform(get("/users")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].username").value("username"));

  }

  @Test
  void mustFindUserById() throws Exception {
    String userId = insertUser();

    mockLoginToken("username", "password");

    mockMvc
        .perform(
            get("/users/" + userId).headers(httpHeaders).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.username").value("username"));
  }

  @Test
  void mustFindUserByName() throws Exception {
    insertUser();

    mockLoginToken("username", "password");

    mockMvc
        .perform(get("/users/name/username").headers(httpHeaders)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.username").value("username"));
  }

  @Test
  void mustSaveUser() throws Exception {
    UserDto userDto = new UserDto("TEST_USER", "password", Role.ADMIN, true);

    String body = objectMapper.writeValueAsString(userDto);

    mockMvc
        .perform(post("/users").headers(httpHeaders).contentType(MediaType.APPLICATION_JSON)
            .content(body))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.username").value("TEST_USER"))
        .andExpect(jsonPath("$.role").value("ADMIN"));

  }

  @Test
  void mustDeleteUser() throws Exception {
    String userId = insertUser();

    mockLoginToken("username", "password");

    mockMvc
        .perform(
            delete("/users/" + userId).headers(httpHeaders).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  @Test
  void mustUpdateUser() throws Exception {
    String userId = insertUser();

    mockLoginToken("username", "password");

    UserDto userDto = new UserDto("USER_UPDATED", "password", Role.ADMIN, true);

    String body = objectMapper.writeValueAsString(userDto);

    mockMvc
        .perform(put("/users/" + userId).headers(httpHeaders)
            .contentType(MediaType.APPLICATION_JSON).content(body))
        .andExpect(status().isAccepted())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").isNotEmpty())
        .andExpect(jsonPath("$.username").value("USER_UPDATED"));
  }

  // ---------- MEASURE TESTS ----------

  @Test
  void mustFindAllMeasures() throws Exception {
    insertUser();

    mockLoginToken("username", "password");

    String isleId = insertIsle();

    String measureId = insertMeasure(isleId);

    mockMvc.perform(get("/measures").headers(httpHeaders).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].id").value(measureId));
  }

  @Test
  void mustFindMeasureById() throws Exception {
    insertUser();

    mockLoginToken("username", "password");

    String isleId = insertIsle();

    String measureId = insertMeasure(isleId);

    mockMvc
        .perform(get("/measures/" + measureId).headers(httpHeaders)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(measureId));
  }

  @Test
  void mustFindMeasuresByIsle() throws Exception {
    insertUser();

    mockLoginToken("username", "password");

    String isleId = insertIsle();

    String measureId = insertMeasure(isleId);

    mockMvc
        .perform(get("/measures/byisle/" + isleId).headers(httpHeaders)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].id").value(measureId));
  }

  @Test
  void mustSaveMeasure() throws Exception {
    insertUser();

    mockLoginToken("username", "password");

    String isleId = insertIsle();

    MeasureDto measureDto = new MeasureDto(isleId, 10, 10, 10, 10, 10, 10, 10, Direction.WEST);

    String body = objectMapper.writeValueAsString(measureDto);

    mockMvc
        .perform(post("/measures").headers(httpHeaders).contentType(MediaType.APPLICATION_JSON)
            .content(body))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated()).andExpect(jsonPath("$.id").isNotEmpty())
        .andExpect(jsonPath("$.timestamp").isNotEmpty())
        .andExpect(jsonPath("$.isleId").value(measureDto.getIsleId()))
        .andExpect(
            jsonPath("$.airTemperature").value(String.valueOf(measureDto.getAirTemperature())))
        .andExpect(jsonPath("$.groundTemperature")
            .value(String.valueOf(measureDto.getGroundTemperature())))
        .andExpect(jsonPath("$.windSpeed").value(String.valueOf(measureDto.getWindSpeed())))
        .andExpect(jsonPath("$.windDirection")
            .value(String.valueOf(measureDto.getWindDirection().toString())))
        .andExpect(
            jsonPath("$.solarRadiation").value(String.valueOf(measureDto.getSolarRadiation())))
        .andExpect(jsonPath("$.airHumidity").value(String.valueOf(measureDto.getAirHumidity())))
        .andExpect(
            jsonPath("$.groundHumidity").value(String.valueOf(measureDto.getGroundHumidity())))
        .andExpect(jsonPath("$.precipitation").value(String.valueOf(measureDto.getPrecipitation())))
        .andReturn();
  }

  @Test
  void mustDeleteMeasure() throws Exception {
    insertUser();

    mockLoginToken("username", "password");

    String isleId = insertIsle();

    String measureId = insertMeasure(isleId);

    mockMvc.perform(delete("/measures/" + measureId).headers(httpHeaders)
        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

  }

  @Test
  void mustUpdateMeasure() throws Exception {
    insertUser();

    mockLoginToken("username", "password");

    String isleId = insertIsle();

    String measureId = insertMeasure(isleId);

    MeasureDto measureDto = new MeasureDto(isleId, 10, 10, 10, 10, 10, 10, 10, Direction.EAST);

    String body = objectMapper.writeValueAsString(measureDto);

    mockMvc
        .perform(put("/measures/" + measureId).headers(httpHeaders)
            .contentType(MediaType.APPLICATION_JSON).content(body))
        .andExpect(status().isAccepted())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(measureId))
        .andExpect(jsonPath("$.windDirection").value("EAST"));
  }

}
