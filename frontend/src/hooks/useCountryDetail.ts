import { useEffect, useState } from 'react';
import {countryRepository} from "../api/countryRepository";
import {Country} from "../api/types/Accommodation";

export const useCountryDetail = (id: number) => {
    const [data, setData] = useState<Country | null>(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        if (!id) return;
        countryRepository.getById(id)
            .then(res => setData(res.data))
            .catch(err => setError(err.message))
            .finally(() => setLoading(false));
    }, [id]);

    return { data, loading, error };
};