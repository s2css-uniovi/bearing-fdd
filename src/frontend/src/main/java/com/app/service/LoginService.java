package com.app.service;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.app.dto.UserDTO;

/**
 * Clase LoginService para la conexion con la API de los metodos de gestion de usuarios.
 */
@Service
public class LoginService {

	private final RestTemplate restTemplate;

	/**
	 * Instantiates a new login service.
	 *
	 * @param restTemplate the rest template
	 */
	public LoginService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * Crea el usuario en la bbdd.
	 *
	 * @param info informacion del usuario
	 * @return respuesta de la API
	 * @throws ConnectException the connect exception
	 */
	public String createUserInDB(UserDTO info) throws ConnectException {
		String url = "http://127.0.0.1:5000/registerUser";
		String response = null;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<UserDTO> request = new HttpEntity<>(info, headers);

		try {
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
			response = responseEntity.getBody();
		} catch (ResourceAccessException e) {
			System.err.println("Error de conexión con la API: " + e.getMessage());
			return "1";
		}

		if (response != null) {
			return response;
		} else {
			return "1";
		}
	}

	/**
	 * Check user in DB.
	 *
	 * @param usuario el usuario
	 * @return info del usuario
	 * @throws ConnectException the connect exception
	 */
	public String checkUserInDB(String usuario) throws ConnectException {
		String apiUrl = "http://127.0.0.1:5000/checkUser/" + usuario;

		try {
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
			String response = responseEntity.getBody();

			if (response != null) {
				return response;
			} else {
				throw new RuntimeException("La respuesta de la API es nula.");
			}
		} catch (ResourceAccessException e) {
			System.err.println("Error de conexión con la API: " + e.getMessage());
			return "1";
		} catch (Exception e) {
			System.err.println("Error al procesar la respuesta de la API: " + e.getMessage());
			return "1";
		}
	}

	/**
	 * Gets the current user.
	 *
	 * @param usuario el usuario
	 * @return the current user
	 * @throws ConnectException the connect exception
	 */
	public UserDTO getCurrentUser(String usuario) throws ConnectException {
		String url = "http://127.0.0.1:5000/getUser/" + usuario;
		UserDTO response = null;

		try {
			response = restTemplate.getForObject(url, UserDTO.class);
		} catch (ResourceAccessException e) {
			System.err.println("Error de conexión con la API: " + e.getMessage());
			return response;
		}

		if (response != null) {
			return response;
		} else {
			return response;
		}
	}

	/**
	 * Update current user.
	 *
	 * @param user2Update the user to update
	 * @throws ConnectException the connect exception
	 */
	public void updateCurrentUser(UserDTO user2Update) throws ConnectException {
		String url = "http://127.0.0.1:5000/updateUser/" + user2Update.getUsuario();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<UserDTO> requestEntity = new HttpEntity<>(user2Update, headers);

		RestTemplate restTemplate = new RestTemplate();

		try {
			restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Void.class);
			System.out.println("Dataset actualizado correctamente");
		} catch (Exception e) {
			System.err.println("Error al actualizar el dataset: " + e.getMessage());
		}
	}

	/**
	 * Gets the all users.
	 *
	 * @return lista de todos los usuarios
	 * @throws ConnectException the connect exception
	 */
	public List<UserDTO> getAllUsers() throws ConnectException {
		String url = "http://127.0.0.1:5000/getAllUsers";
        ArrayList<UserDTO> listUsers = new ArrayList<>();

        try {
            ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(url, UserDTO[].class);
            if (response.getStatusCode() == HttpStatus.OK) {
                Collections.addAll(listUsers, response.getBody());
            }
        } catch (ResourceAccessException e) {
            System.err.println("Error de conexión con la API: " + e.getMessage());
            return Collections.emptyList();
        }

        return listUsers;
    }

	/**
	 * Delete user.
	 *
	 * @param user the user
	 * @throws ConnectException the connect exception
	 */
	public void deleteUser(String user) throws ConnectException {
	    String url = "http://127.0.0.1:5000/deleteUser/" + user;

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

	    RestTemplate restTemplate = new RestTemplate();

	    try {
	        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
	        System.out.println("Respuesta del servidor: " + response.getBody());
	    } catch (Exception e) {
	        System.err.println("Error al llamar al método deleteUser: " + e.getMessage());
	    }
	}
}
