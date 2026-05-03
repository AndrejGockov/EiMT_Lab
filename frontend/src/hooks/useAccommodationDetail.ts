import { useEffect, useState } from 'react';
import { accommodationRepository } from '../api/accommodationRepository';
import {Accommodation} from "../api/types/Accommodation";


export const useAccommodationDetail = (id: number) => {
    const [data, setData] = useState<Accommodation | null>(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        if (!id) return;
        accommodationRepository.getById(id)
            .then(res => setData(res.data))
            .catch(err => setError(err.message))
            .finally(() => setLoading(false));
    }, [id]);

    return { data, loading, error };
};