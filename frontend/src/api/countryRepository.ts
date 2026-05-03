import {Country} from "./types/Accommodation";
import axiosInstance from "../axios/axios";

export const countryRepository = {
    getAll: () => axiosInstance.get<Country[]>('/countries'),
    getById: (id: number) => axiosInstance.get<Country>(`/countries/${id}`),
};