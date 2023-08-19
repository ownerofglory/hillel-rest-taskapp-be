package ua.ithillel.hilleltask;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ua.ithillel.hilleltask.model.dto.BoardDTO;
import ua.ithillel.hilleltask.service.BoardService;
import ua.ithillel.hilleltask.service.BoardServiceDefault;

import java.util.List;

@Path("boards")
public class BoardsResource {
    @Inject
    private BoardService boardService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BoardDTO> getAll() {
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
        return boardService.getBoardById(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BoardDTO edit(@PathParam("id") Integer id, BoardDTO boardDTO) {
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
