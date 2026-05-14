import {Accommodation} from "./types/Accommodation";
import axiosInstance from "../axios/axios";

export interface AccommodationRequest {
    name: string;
    category: string;
    condition: string;
    numRooms: number;
    hostId: number;
    rented?: boolean;
    workStartDate?: string;
}

export const accommodationRepository = {
    getAll: () => axiosInstance.get<Accommodation[]>('/accommodations'),
    getById: (id: number) => axiosInstance.get<Accommodation>(`/accommodations/${id}`),
    create: (data: AccommodationRequest) => axiosInstance.post('/accommodations/add', data),
    update: (id: number, data: AccommodationRequest) => axiosInstance.post(`/accommodations/update/${id}`, data),
    delete: (id: number) => axiosInstance.delete(`/accommodations/delete/${id}`),
};