package rw.app.urugendo.day.services.ticket.requests.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rw.app.urugendo.day.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.RequestedTicketRoute;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.dto.CreateTicketRouteRequest;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.dto.RequestTicketRouteDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.dto.RequestUpdatingDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.utils.RequestedTicketRouteMapper;
import rw.app.urugendo.day.models.schoolmanagement.day.DaySchool;
import rw.app.urugendo.day.repositories.schoolmanagement.day.DaySchoolRepo;
import rw.app.urugendo.day.repositories.tickets.requests.TicketRequestsRepo;
import rw.app.urugendo.day.services.ticket.requests.TicketRoutesRequest;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.enums.ERouteRequestStatus;
import rw.app.urugendo.day.services.usermanagement.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketRoutesRequestImpl implements TicketRoutesRequest {
    private final TicketRequestsRepo requestsRepo;
    private final DaySchoolRepo daySchoolRepo;
    private final UserServiceImpl userService;

    private final String requestedBy = userService.getCurrentUser().getParent().getEmail();

    @Override
    public RequestTicketRouteDto requestRoute(CreateTicketRouteRequest createTicketRouteRequest) {
        RequestTicketRouteDto routeDto = null;
        try {
            RequestedTicketRoute toBeRequested = RequestedTicketRouteMapper.createTicketRouteToRequestedRoute(createTicketRouteRequest);
            RequestedTicketRoute requested = requestsRepo.save(toBeRequested);
            routeDto = RequestedTicketRouteMapper.routeTorouteDto(requested);
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }
        return routeDto;
    }

    @Override
    public RequestTicketRouteDto updateRequest(UUID requestId, RequestUpdatingDto requestUpdatingDto) {
        RequestTicketRouteDto routeDto = null;
        try {
            Optional<RequestedTicketRoute> toBeUpdated = requestsRepo.findById(requestId);
            if (toBeUpdated.isEmpty()) throw new ResourceNotFoundException("Requested ticket_route not found");

            Optional<DaySchool> school = daySchoolRepo.findById(requestUpdatingDto.getSchoolId());
            if (school.isEmpty())
                throw new ResourceNotFoundException("School with such id " + requestUpdatingDto.getSchoolId() + " not found");

            toBeUpdated.get().setDeparturePoint(requestUpdatingDto.getDeparturePoint());
            toBeUpdated.get().setDestinationPoint(requestUpdatingDto.getDestinationPoint());
            toBeUpdated.get().setRequestedBy(requestUpdatingDto.getRequestedBy());
            toBeUpdated.get().setNOfSeatsRequested(requestUpdatingDto.getNOfSeatsRequested());
            toBeUpdated.get().setSchoolId(requestUpdatingDto.getSchoolId());

            routeDto = RequestedTicketRouteMapper.routeTorouteDto(requestsRepo.save(toBeUpdated.get()));
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }
        return routeDto;

    }

    @Override
    public RequestTicketRouteDto getSingleRequest(UUID requestId) {
        RequestTicketRouteDto routeDto = null;
        try {
            Optional<RequestedTicketRoute> routeRequested = requestsRepo.findById(requestId);
            if (routeRequested.isEmpty()) throw new ResourceNotFoundException("Ticket route requested not found");

            routeDto = RequestedTicketRouteMapper.routeTorouteDto(routeRequested.get());
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return routeDto;
    }

    @Override
    public List<RequestTicketRouteDto> getAllRequests() {
        List<RequestTicketRouteDto> routeDtos = new ArrayList<>();
        try {
            List<RequestedTicketRoute> routesRequested = requestsRepo.findAll();
            routeDtos = routesRequested
                    .stream()
                    .map(RequestedTicketRouteMapper::routeTorouteDto)
                    .toList();
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return routeDtos;
    }

    @Override
    public List<RequestTicketRouteDto> getAllByRequestedBy() {
        List<RequestTicketRouteDto> routeDtos = new ArrayList<>();
        try {
            List<RequestedTicketRoute> routesRequested = requestsRepo.findRequestedTicketRoutesByRequestedBy(requestedBy);
            routeDtos = routesRequested
                    .stream()
                    .map(RequestedTicketRouteMapper::routeTorouteDto)
                    .toList();
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return routeDtos;
    }

    @Override
    public List<RequestTicketRouteDto> getAllBySchoolId(UUID schoolId) {
        List<RequestTicketRouteDto> routeDtos = new ArrayList<>();
        try {
            List<RequestedTicketRoute> routesRequested = requestsRepo.findRequestedTicketRoutesBySchoolId(schoolId);
            routeDtos = routesRequested
                    .stream()
                    .map(RequestedTicketRouteMapper::routeTorouteDto)
                    .toList();
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return routeDtos;
    }

    @Override
    public boolean deleteRequest(UUID requestId) {
        boolean isDeleted = false;
        try {
            Optional<RequestedTicketRoute> toBeDeleted = requestsRepo.findById(requestId);
            if (toBeDeleted.isEmpty()) throw new ResourceNotFoundException("Ticket route requested not found");

            requestsRepo.delete(toBeDeleted.get());
            isDeleted = !requestsRepo.existsById(requestId);
        } catch (ResourceNotFoundException e) {
            log.error("Something bad happened: {}", e.getMessage());
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
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return rejectedRequest;
    }
}
