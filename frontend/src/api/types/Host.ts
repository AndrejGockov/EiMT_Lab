import {Country} from "./Accommodation";

export interface Host {
    id: number;
    name: string;
    surname: string;
    email: string;
    country: Country;
}