export interface Accommodation {
    id: number;
    name: string;
    category: string;
    condition: string;
    rented: boolean;
    numRooms: number;
    host: {
        id: number;
        name: string;
        surname?: string;
        email?: string;
        country?: Country;
    };
    workStartDate?: string;
    createdAt?: string;
    updatedAt?: string;
}


export interface Country {
    id: number;
    name: string;
}
