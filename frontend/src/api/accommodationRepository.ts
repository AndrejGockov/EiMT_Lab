import {Accommodation} from "./types/Accommodation";
import axiosInstance from "../axios/axios";


export const accommodationRepository = {
    getAll: () => axiosInstance.get<Accommodation[]>('/accommodations'),
    getById: (id: number) => axiosInstance.get<Accommodation>(`/accommodations/${id}`),
};