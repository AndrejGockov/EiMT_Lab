import {Host} from "./types/Host";
import axiosInstance from "../axios/axios";


export const hostRepository = {
    getAll: () => axiosInstance.get<Host[]>('/hosts'),
    getById: (id: number) => axiosInstance.get<Host>(`/hosts/${id}`),
};