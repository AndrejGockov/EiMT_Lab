import { useEffect, useState } from 'react';
import { hostRepository } from '../api/hostRepository';
import {Host} from "../api/types/Host";

export const useHosts = () => {
    const [data, setData] = useState<Host[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        hostRepository.getAll()
            .then(res => setData(res.data))
            .catch(err => setError(err.message))
            .finally(() => setLoading(false));
    }, []);

    return { data, loading, error };
};