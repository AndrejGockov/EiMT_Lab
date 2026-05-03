import { useEffect, useState } from 'react';
import { accommodationRepository } from '../api/accommodationRepository';
import {Accommodation} from "../api/types/Accommodation";

export const useAccommodations = () => {
    const [data, setData] = useState<Accommodation[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        accommodationRepository.getAll()
            .then(res => setData(res.data))
            .catch(err => setError(err.message))
            .finally(() => setLoading(false));
    }, []);

    return { data, loading, error };
};