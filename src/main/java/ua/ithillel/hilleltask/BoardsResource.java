package ua.ithillel.hilleltask;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import ua.ithillel.hilleltask.model.dto.BoardDTO;
import ua.ithillel.hilleltask.service.BoardService;

import java.util.List;
//import java.util.logging.Logger;

@Path("boards")
public class BoardsResource {
//    private static final Logger LOGGER = Logger.getLogger(BoardsResource.class.getName());
//    private static final Logger LOGGER = LogManager.getLogger(BoardsResource.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(BoardsResource.class);

    @Inject
    private BoardService boardService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BoardDTO> getAll() {
        String path = MDC.get("path");


        LOGGER.info("Get All boards");
        return boardService.getAllBoards();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BoardDTO add(BoardDTO boardDTO) {
        return boardService.createBoard(boardDTO);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BoardDTO get(@PathParam("id") Integer id) {
        LOGGER.info("Get boards by id");
        LOGGER.debug("Board id: {}", id);
        return boardService.getBoardById(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BoardDTO edit(@PathParam("id") Integer id, BoardDTO boardDTO) {
        LOGGER.debug("Editing Board {}, id {}", boardDTO, id);
        try {
            return boardService.editBoard(id, boardDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BoardDTO delete(@PathParam("id") Integer id) {
        try {
            return boardService.deleteBoard(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
