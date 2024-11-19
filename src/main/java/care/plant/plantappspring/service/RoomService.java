package care.plant.plantappspring.service;

import care.plant.plantappspring.model.Room;
import care.plant.plantappspring.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    // Constructor for dependency injection
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // Add a new room
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    // Get a room by ID
    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    // Get a room by name
    public Optional<Room> getRoomByName(String roomName) {
        return roomRepository.findByRoomName(roomName);
    }

    // Get all rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Query rooms by humidity level
    public List<Room> getRoomsByHumidityLevel(String humidityLevel) {
        return roomRepository.findByHumidityLevel(humidityLevel);
    }

    // Query rooms by light level
    public List<Room> getRoomsByLightLevel(String lightLevel) {
        return roomRepository.findByLightLevel(lightLevel);
    }

    // Query rooms by window orientation
    public List<Room> getRoomsByWindowFacing(String windowFacing) {
        return roomRepository.findByWindowFacing(windowFacing);
    }

    // Query rooms sorted by name
    public List<Room> getRoomsSortedByName() {
        return roomRepository.findAllByOrderByRoomNameAsc();
    }

    // Delete a room by ID
    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }
}