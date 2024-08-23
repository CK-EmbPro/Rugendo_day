package rw.app.urugendo.day.services.schoolmanagement.day.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rw.app.urugendo.day.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.day.models.Notifications.dto.CreateNotificationDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.RequestedTicketRoute;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.dto.RequestTicketRouteDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.enums.ERouteRequestStatus;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.utils.RequestedTicketRouteMapper;
import rw.app.urugendo.day.models.schoolmanagement.day.DaySchool;
import rw.app.urugendo.day.models.schoolmanagement.day.dto.CreateDaySchoolDto;
import rw.app.urugendo.day.models.schoolmanagement.day.dto.DaySchoolDto;
import rw.app.urugendo.day.models.schoolmanagement.day.utils.DaySchoolMapper;
import rw.app.urugendo.day.repositories.schoolmanagement.day.DaySchoolRepo;
import rw.app.urugendo.day.repositories.tickets.requests.TicketRequestsRepo;
import rw.app.urugendo.day.services.notifications.impl.NotifyServiceImpl;
import rw.app.urugendo.day.services.schoolmanagement.day.DaySchoolService;
import rw.app.urugendo.day.services.usermanagement.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DaySchoolServiceImpl implements DaySchoolService {
    private final DaySchoolRepo schoolRepo;
    private final TicketRequestsRepo requestsRepo;
    private final UserServiceImpl userService;
    private final NotifyServiceImpl notifyService;

    @Override
    public DaySchoolDto registerDaySchool(CreateDaySchoolDto createDaySchoolDto) {
        DaySchoolDto registeredSchool = null;
        try {
            DaySchool toBeSaved = DaySchoolMapper.createDaySchoolDtoToDaySchool(createDaySchoolDto);
            DaySchool savedSchool = schoolRepo.save(toBeSaved);
            registeredSchool = DaySchoolMapper.daySchoolToDaySchoolDto(savedSchool);

        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }
        return registeredSchool;
    }

    @Override
    public DaySchoolDto updateDaySchool(UUID schoolId, DaySchoolDto daySchoolDto) {
        DaySchoolDto updatedSchoolDto = null;
        try {
            Optional<DaySchool> toBeUpdated = schoolRepo.findById(schoolId);
            if (toBeUpdated.isEmpty()) throw new ResourceNotFoundException("school not found");
//Updating the school
            toBeUpdated.get().setSchoolName(daySchoolDto.getSchoolName());
            toBeUpdated.get().setSchoolCode(daySchoolDto.getSchoolCode());
            toBeUpdated.get().setSchoolDistrict(daySchoolDto.getSchoolDistrict());
            toBeUpdated.get().setSchoolProvince(daySchoolDto.getSchoolProvince());
            toBeUpdated.get().setSchoolEmail(daySchoolDto.getSchoolEmail());
            toBeUpdated.get().setSchoolPhono(daySchoolDto.getSchoolEmail());


            DaySchool updatedSchool = schoolRepo.save(toBeUpdated.get());
            updatedSchoolDto = DaySchoolMapper.daySchoolToDaySchoolDto(updatedSchool);

        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return updatedSchoolDto;
    }

    @Override
    public DaySchoolDto getSchoolByCode(String schoolCode) {
        DaySchoolDto schoolDto = null;
        try {
            Optional<DaySchool> school = schoolRepo.findDaySchoolBySchoolCode(schoolCode);
            if (school.isEmpty()) throw new ResourceNotFoundException("school not found");
            schoolDto = DaySchoolMapper.daySchoolToDaySchoolDto(school.get());
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }
        return schoolDto;
    }

    @Override
    public List<DaySchoolDto> getAllSchools() {
        List<DaySchoolDto> schoolDtos = new ArrayList<>();
        try {
            List<DaySchool> schools = schoolRepo.findAll();
            schoolDtos = schools
                    .stream()
                    .map(DaySchoolMapper::daySchoolToDaySchoolDto)
                    .toList();
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }

        return schoolDtos;
    }

    @Override
    public DaySchoolDto getSchoolById(UUID schoolId) {
        DaySchoolDto schoolDto = null;
        try {
            Optional<DaySchool> school = schoolRepo.findById(schoolId);
            if (school.isEmpty()) throw new ResourceNotFoundException("school not found");
            schoolDto = DaySchoolMapper.daySchoolToDaySchoolDto(school.get());
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }
        return schoolDto;
    }

    @Override
    public boolean deleteSchoolByCode(String schoolCode) {
        boolean isDeleted = false;
        try {
            Optional<DaySchool> school = schoolRepo.findDaySchoolBySchoolCode(schoolCode);
            if (school.isEmpty()) throw new ResourceNotFoundException("school not found");
            schoolRepo.delete(school.get());
            Optional<DaySchool> isSchoolPresent = schoolRepo.findDaySchoolBySchoolCode(schoolCode);

            isDeleted = isSchoolPresent.isEmpty();
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return isDeleted;
    }

    @Override
    public boolean deleteSchoolById(UUID schoolId) {
        boolean isDeleted = false;
        try {
            Optional<DaySchool> school = schoolRepo.findById(schoolId);
            if (school.isEmpty()) throw new ResourceNotFoundException("school not found");
            schoolRepo.delete(school.get());
            isDeleted = !schoolRepo.existsById(schoolId);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return isDeleted;
    }

    @Override
    public RequestTicketRouteDto approveRequest(UUID requestId) {
        RequestTicketRouteDto approvedRequest = null;
        try {
            Optional<RequestedTicketRoute> toBeApproved = requestsRepo.findById(requestId);
            if (toBeApproved.isEmpty()) throw new ResourceNotFoundException("Ticket route requested not found");

            toBeApproved.get().setRouteRequestStatus(ERouteRequestStatus.APPROVED);
            RequestedTicketRoute approved = requestsRepo.save(toBeApproved.get());
            approvedRequest = RequestedTicketRouteMapper.routeTorouteDto(approved);
            String currentUser = userService.getCurrentUser().getParent().getEmail();

            CreateNotificationDto notifySchool = CreateNotificationDto.builder()
                    .sentTo(currentUser)
                    .message("Congratulations !! you've managed to approve request for a new route")
                    .triggeringAction("DELETION OF REQUEST FOR NEW SCHOOL_BUS TICKET ROUTE")
                    .build();

            CreateNotificationDto notifyParent = CreateNotificationDto.builder()
                    .sentTo(approvedRequest.getRequestedBy())
                    .message("Congratulations !! your request for new ticket_route from "+approvedRequest.getDeparturePoint()+" to "+approvedRequest.getDestinationPoint()+" has been approved.")
                    .triggeringAction("APPROVAL OF REQUEST FOR NEW SCHOOL_BUS TICKET ROUTE")
                    .build();

            notifyService.registerNotify(notifySchool);
            notifyService.registerNotify(notifyParent);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return approvedRequest;
    }

    @Override
    public RequestTicketRouteDto rejectRequest(UUID requestId) {
        RequestTicketRouteDto rejectedRequest = null;
        try {
            Optional<RequestedTicketRoute> toBeApproved = requestsRepo.findById(requestId);
            if (toBeApproved.isEmpty()) throw new ResourceNotFoundException("Ticket route requested not found");

            toBeApproved.get().setRouteRequestStatus(ERouteRequestStatus.REJECTED);
            RequestedTicketRoute rejected = requestsRepo.save(toBeApproved.get());
            rejectedRequest = RequestedTicketRouteMapper.routeTorouteDto(rejected);

            String currentUser = userService.getCurrentUser().getParent().getEmail();

            CreateNotificationDto notifySchool = CreateNotificationDto.builder()
                    .sentTo(currentUser)
                    .message("Congratulations !! you've managed to approve request for a new route")
                    .triggeringAction("DELETION OF REQUEST FOR NEW SCHOOL_BUS TICKET ROUTE")
                    .build();

            CreateNotificationDto notifyParent = CreateNotificationDto.builder()
                    .sentTo(rejectedRequest.getRequestedBy())
                    .message("Sorry !! your request for new ticket_route from "+rejectedRequest.getDeparturePoint()+" to "+rejectedRequest.getDestinationPoint()+" has been rejected.")
                    .triggeringAction("REJECTION OF REQUEST FOR NEW SCHOOL_BUS TICKET ROUTE")
                    .build();

            notifyService.registerNotify(notifySchool);
            notifyService.registerNotify(notifyParent);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return rejectedRequest;
    }
}
