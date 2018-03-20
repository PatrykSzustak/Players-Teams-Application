//package com.kchmielewski.sda.java.spring01java.web;
//
//import com.kchmielewski.sda.java.spring01java.service.PlayerService;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.MessageSource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.ui.Model;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.mock;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
//
//
//public class PlayerControllerTest {
//    private final MessageSource messageSource = mock(MessageSource.class);
//    private final PlayerController controller = new PlayerController(new PlayerService(), messageSource);
//
//    private MockMvc mvc;
//
//    @Before
//    public void setup() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/view/");
//        viewResolver.setSuffix(".jsp");
//        mvc = standaloneSetup(controller).setViewResolvers(viewResolver).build();
//    }
//
//    @Test
//    public void displayTest() throws Exception {
//        MvcResult mvcResult = mvc.perform(get("playersPage")).andExpect(status().isOk()).andReturn();
////        assertThat(mvcResult.getModelAndView().getViewName()).isEqualTo("playersPage");
//    }
//
//
////    @Test
////    public void addTest() throws Exception {
////        MvcResult mvcResult = mvc.perform(post("playersPage")
////                .param("name", "Artur").param("surname", "Boruc"))
////                .andExpect(status().isOk()).andReturn();
////
////        assertThat(mvcResult.getModelAndView().getViewName()).isEqualTo("playersPage");
////
////    }
////
////    @Test
////    public void removeTest() throws Exception {
////
////        MvcResult mvcResult = mvc.perform(post("/remove")
////                .param("name", "Artur").param("surname", "Boruc"))
////                .andExpect(status().isOk()).andReturn();
////
////        assertThat(mvcResult.getModelAndView().getViewName()).isEqualTo("playersPage");
////
////    }
//
//
//     /*@Test
//    public void superFancyMethodReturnsWhatItShouldReturn() throws Exception {
//        MvcResult mvcResult = mvc.perform(get(("/extremelyFancyMethodMappingWithEvenMoreFancyEverythingAndNothingAtTheSameTime")))
//                .andExpect(status().isOk()).andReturn();
//
//        assertThat(mvcResult.getModelAndView().getViewName()).isEqualTo("playersPage");
//    }*/
//
//
//}
