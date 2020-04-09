package rusoft.project.unit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import rusoft.project.Application;
import rusoft.project.dto.AbstractRentDto;
import rusoft.project.dto.RentEndDto;
import rusoft.project.dto.RentStartDto;
import rusoft.project.model.ResponseStatus;
import rusoft.project.service.RentService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
class RentControllerTest {

    private MockMvc mvc;
    private WebApplicationContext webApplicationContext;
    private String addClientRequest;
    private String deleteClientRequest;
    @MockBean
    private RentService rentService;
    private RentStartDto rentStartDto;
    private RentEndDto rentEndDto;

    @Autowired
    public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        rentStartDto = new RentStartDto()
                .setClientName("Ivan")
                .setBirthYear(1990L)
                .setCarBrand("BMW")
                .setManufactureYear(2019L);
        rentEndDto = new RentEndDto()
                .setClientName("Ivan")
                .setCarBrand("BMW");
        addClientRequest = createJsonRequestFromRentData(rentStartDto);
        deleteClientRequest = createJsonRequestFromRentData(rentEndDto);
    }

    private String createJsonRequestFromRentData(AbstractRentDto rentDto) {
        try {
            return mapToJson(rentDto);
        } catch (JsonProcessingException e) {
            fail("fail to parse rent data");
        }
        return "";
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    private int getStatusAfterTryToAddClient(String clientRequest) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.put("/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(clientRequest))
                .andReturn().getResponse().getStatus();
    }

    private int getStatusAfterTryToDeleteClient(String clientRequest) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.delete("/delete")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(clientRequest))
                .andReturn().getResponse().getStatus();
    }

    @Test
    void addClientWhenActualDataIsOk() throws Exception {
        when(rentService.addClient(Mockito.any(RentStartDto.class))).thenReturn(ResponseStatus.OK);
        int status = getStatusAfterTryToAddClient(addClientRequest);
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    void addClientWhenIncorrectDataBadRequest() throws Exception {
        when(rentService.addClient(Mockito.any(RentStartDto.class))).thenReturn(ResponseStatus.OK);
        int status = getStatusAfterTryToAddClient("Some string");
        assertEquals(HttpStatus.BAD_REQUEST.value(), status);
    }

    @Test
    void deleteClientWhenActualDataIsOk() throws Exception {
        when(rentService.removeClient(Mockito.any(RentEndDto.class))).thenReturn(ResponseStatus.OK);
        int status = getStatusAfterTryToDeleteClient(deleteClientRequest);
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    void deleteClientWhenIncorrectDataBadRequest() throws Exception {
        when(rentService.removeClient(Mockito.any(RentEndDto.class))).thenReturn(ResponseStatus.OK);
        int status = getStatusAfterTryToDeleteClient("Some string");
        assertEquals(HttpStatus.BAD_REQUEST.value(), status);
    }
}