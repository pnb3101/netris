package netris;

import domain.AggregateData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import service.AggregateService;

import java.util.HashSet;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AggregateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AggregateService aggregateService;

    @Test
    public void testAggregateControllerCode()throws Exception{
        when(aggregateService.aggregate()).thenReturn(new HashSet<AggregateData>());
        mockMvc.perform(get("/aggregate")).andDo(print()).andExpect(status().is2xxSuccessful());
    }


}
