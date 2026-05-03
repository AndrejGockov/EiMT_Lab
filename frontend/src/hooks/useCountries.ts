import { useEffect, useState } from 'react';
import {countryRepository} from "../api/countryRepository";
import {Country} from "../api/types/Accommodation";

export const useCountries = () => {
    const [data, setData] = useState<Country[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        countryRepository.getAll()
            .then(res => setData(res.data))
            .catch(err => setError(err.message))
            .finally(() => setLoading(false));
    }, []);

    return { data, loading, error };
};