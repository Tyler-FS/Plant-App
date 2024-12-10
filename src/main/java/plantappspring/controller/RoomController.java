package main.java.plantappspring.controller;

import plantappspring.model.room.Room;
import plantappspring.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // Add a new room
    @PostMapping
    public Room addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }

    // Get a room by ID
    @GetMapping("/{id}")
    public Optional<Room> getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    // Get a room by name
    @GetMapping("/by-name")
    public Optional<Room> getRoomByName(@RequestParam String roomName) {
        return roomService.getRoomByName(roomName);
    }

    // Get all rooms
    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    // Get rooms by humidity level
    @GetMapping("/by-humidity")
    public List<Room> getRoomsByHumidityLevel(@RequestParam String humidityLevel) {
        return roomService.getRoomsByHumidityLevel(humidityLevel);
    }

    // Get rooms by light level
    @GetMapping("/by-light")
    public List<Room> getRoomsByLightLevel(@RequestParam String lightLevel) {
        return roomService.getRoomsByLightLevel(lightLevel);
    }

    // Get rooms by window orientation
    @GetMapping("/by-window")
    public List<Room> getRoomsByWindowFacing(@RequestParam String windowFacing) {
        return roomService.getRoomsByWindowFacing(windowFacing);
    }

    // Get rooms sorted by name
    @GetMapping("/sorted")
    public List<Room> getRoomsSortedByName() {
        return roomService.getRoomsSortedByName();
    }

    // Delete a room by ID
    @DeleteMapping("/{id}")
    public void deleteRoomById(@PathVariable Long id) {
        roomService.deleteRoomById(id);
    }

    // Get notifications for room conditions
    @GetMapping("/{id}/notifications")
    public String getRoomNotifications(@PathVariable Long id) {
        return roomService.getRoomNotifications(id);
    }

    // Get adjustment suggestions for room conditions
    @GetMapping("/{id}/adjustments")
    public String getRoomAdjustments(@PathVariable Long id) {
        return roomService.getRoomAdjustments(id);
    }
}

