package de.javaakademie.guests.rest;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import com.google.gson.Gson;

import de.javaakademie.guests.model.Guest;
import de.javaakademie.guests.persistence.GuestDAO;
import spark.Spark;

public class GuestResource {

	public static void start() {
		GuestDAO guestDAO = new GuestDAO();

		post("/guests", (request, response) -> {
			response.type("application/json");

			Guest guest = new Gson().fromJson(request.body(), Guest.class);
			Optional<Guest> guestOpt = guestDAO.persist(guest);
			if (guestOpt.isPresent()) {
				return new Gson().toJson(new Response(StatusResponse.SUCCESS));
			} else {
				return new Gson()
						.toJson(new Response(StatusResponse.ERROR, new Gson().toJson("Error occurs while persisting")));
			}

		});

		get("/guests", (request, response) -> {
			response.type("application/json");
			return new Gson()
					.toJson(new Response(StatusResponse.SUCCESS, new Gson().toJsonTree(guestDAO.getEntities())));
		});

		get("/guests/:id", (request, response) -> {
			response.type("application/json");
			Optional<Guest> guestOpt = guestDAO.get(request.params(":id"));
			if (guestOpt.isPresent()) {
				return new Gson().toJson(new Response(StatusResponse.SUCCESS, new Gson().toJsonTree(guestOpt.get())));
			} else {
				return new Gson().toJson(new Response(StatusResponse.ERROR, new Gson().toJson("Guest not found")));
			}
		});

		put("/guests/:id", (request, response) -> {
			response.type("application/json");

			Guest toUpdate = new Gson().fromJson(request.body(), Guest.class);
			toUpdate.setId(request.params(":id"));
			Optional<Guest> guestOpt = guestDAO.update(toUpdate);
			if (guestOpt.isPresent()) {
				return new Gson().toJson(new Response(StatusResponse.SUCCESS, new Gson().toJsonTree(guestOpt.get())));
			} else {
				return new Gson().toJson(new Response(StatusResponse.ERROR,
						new Gson().toJson("Guest not found or error while updating")));
			}
		});

		delete("/guests/:id", (request, response) -> {
			response.type("application/json");

			Optional<Guest> guestOpt = guestDAO.get(request.params(":id"));
			if (guestOpt.isPresent()) {
				guestDAO.remove(guestOpt.get());
				return new Gson().toJson(new Response(StatusResponse.SUCCESS, "guest deleted"));
			} else {
				return new Gson().toJson(new Response(StatusResponse.ERROR, "Guest not found or error while deleting"));
			}
		});

		options("/guests/:id", (request, response) -> {
			response.type("application/json");
			boolean guestExist = false;
			Optional<Guest> guestOpt = guestDAO.get(request.params(":id"));
			if (guestOpt.isPresent()) {
				guestExist = true;
			}
			return new Gson().toJson(
					new Response(StatusResponse.SUCCESS, (guestExist) ? "Guest exists" : "Guest does not exists"));
		});

		get("/stop", (request, response) -> {
			Spark.stop();
			response.type("application/json");
			return new Gson().toJson(new Response(StatusResponse.SUCCESS, "GuestService stopped"));
		});

		get("/modules", (request, response) -> {
			String moduleList = "";
			ModuleLayer layer = GuestResource.class.getModule().getLayer();
			Set<Module> modules = layer.modules();
			Iterator<Module> iterator = modules.iterator();
			while (iterator.hasNext()) {
				Module module = iterator.next();
				if (module != null) {
					String name = module.getName();
					moduleList += name + ", ";
				}
			}
			response.type("application/json");
			return new Gson().toJson(new Response(StatusResponse.SUCCESS, moduleList));
		});

	}
}
