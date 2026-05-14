import { useEffect, useState, useCallback } from 'react';
import { accommodationRepository } from '../api/accommodationRepository';
import {Accommodation} from "../api/types/Accommodation";

export const useAccommodations = () => {
    const [data, setData] = useState<Accommodation[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    const fetchData = useCallback(() => {
        setLoading(true);
        accommodationRepository.getAll()
            .then(res => setData(res.data))
            .catch(err => setError(err.message))
            .finally(() => setLoading(false));
    }, []);

    useEffect(() => {
        fetchData();
    }, [fetchData]);

    return { data, loading, error, refetch: fetchData };
};